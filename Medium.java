package Latsol_UTS;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Vehicle {
    protected String kode;
    protected String nama;
    protected long harga;
    protected boolean tersedia;

    public Vehicle(String kode, String nama, long harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true;
    }

    public String getKode() {
        return kode;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public abstract String getTipe();

    public abstract long getPotonganPromo();

    public void cetakDetail() {
        String status = tersedia ? "TERSEDIA" : "DISEWA";
        System.out.println(kode + " | " + getTipe() + " | " + nama + " | harga: " + harga + " | status: " + status);
    }

    public long hitungSewa(int hari, boolean isPromo) {
        long total = harga * hari;
        if (isPromo) {
            total -= getPotonganPromo();
        }
        return Math.max(total, 0);
    }
}

class Car extends Vehicle {
    public Car(String kode, String nama, long harga) {
        super(kode, nama, harga);
    }

    @Override
    public String getTipe() {
        return "CAR";
    }

    @Override
    public long getPotonganPromo() {
        return 20000;
    }
}

class Bike extends Vehicle {
    public Bike(String kode, String nama, long harga) {
        super(kode, nama, harga);
    }

    @Override
    public String getTipe() {
        return "BIKE";
    }

    @Override
    public long getPotonganPromo() {
        return 10000;
    }
}

public class Medium {

    private static ArrayList<Vehicle> daftarKendaraan = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt())
            return;
        int n = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String barisPerintah = scanner.nextLine().trim();
            if (barisPerintah.isEmpty())
                continue;

            String[] tokens = barisPerintah.split(" ");
            String perintah = tokens[0];

            switch (perintah.toUpperCase()) {
                case "ADD":
                    tambahKendaraan(tokens);
                    break;
                case "RENT":
                    sewaKendaraan(tokens);
                    break;
                case "RETURN":
                    kembalikanKendaraan(tokens);
                    break;
                case "DETAIL":
                    cekDetail(tokens);
                    break;
                case "COUNT":
                    System.out.println("Total kendaraan: " + daftarKendaraan.size());
                    break;
            }
        }
        scanner.close();
    }

    private static Vehicle cariKendaraan(String kode) {
        for (Vehicle v : daftarKendaraan) {
            if (v.getKode().equals(kode)) {
                return v;
            }
        }
        return null;
    }

    private static void tambahKendaraan(String[] tokens) {
        String tipe = tokens[1];
        String kode = tokens[2];
        String nama = tokens[3];
        long harga = Long.parseLong(tokens[4]);

        if (cariKendaraan(kode) != null) {
            System.out.println("Kendaraan sudah terdaftar");
            return;
        }

        if (tipe.equals("CAR")) {
            daftarKendaraan.add(new Car(kode, nama, harga));
        } else if (tipe.equals("BIKE")) {
            daftarKendaraan.add(new Bike(kode, nama, harga));
        }

        System.out.println(tipe + " " + kode + " berhasil ditambahkan");
    }

    private static void sewaKendaraan(String[] tokens) {
        String kode = tokens[1];
        int hari = Integer.parseInt(tokens[2]);
        boolean isPromo = tokens.length == 4 && tokens[3].equalsIgnoreCase("PROMO");

        Vehicle v = cariKendaraan(kode);

        if (v == null) {
            System.out.println("Kendaraan tidak ditemukan");
        } else if (!v.isTersedia()) {
            System.out.println("Kendaraan sedang disewa");
        } else {
            long totalBiaya = v.hitungSewa(hari, isPromo);
            v.setTersedia(false);
            System.out.println("Total sewa " + kode + ": " + totalBiaya);
        }
    }

    private static void kembalikanKendaraan(String[] tokens) {
        String kode = tokens[1];
        Vehicle v = cariKendaraan(kode);

        if (v == null) {
            System.out.println("Kendaraan tidak ditemukan");
        } else if (v.isTersedia()) {
            System.out.println("Kendaraan belum disewa");
        } else {
            v.setTersedia(true);
            System.out.println(kode + " berhasil dikembalikan");
        }
    }

    private static void cekDetail(String[] tokens) {
        String kode = tokens[1];
        Vehicle v = cariKendaraan(kode);

        if (v == null) {
            System.out.println("Kendaraan tidak ditemukan");
        } else {
            v.cetakDetail();
        }
    }
}