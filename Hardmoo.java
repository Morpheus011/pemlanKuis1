package Latsol_UTS;

import java.util.Scanner;

abstract class PerawatanSapi {
    String nama;
    int berat;
    String layanan;
    String kelas;

    PerawatanSapi(String nama, int berat, String layanan, String kelas) {
        this.nama = nama;
        this.berat = berat;
        this.layanan = layanan;
        this.kelas = kelas;
    }

    abstract double getHargaPerKg();

    void cetakNota() {
        double hargaPerKg = getHargaPerKg();
        double biayaDasar = berat * hargaPerKg;

        double diskon = 0;
        if (berat > 30) {
            diskon = biayaDasar * 0.10;
        }

        double tambahanVip = 0;
        if (kelas.equals("vip")) {
            tambahanVip = biayaDasar * 0.20;
        }

        double subtotal = biayaDasar - diskon + tambahanVip;
        double pajak = subtotal * 0.08;
        double totalBiaya = subtotal + pajak;

        boolean isSapiSpesial = nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo");
        if (isSapiSpesial) {
            totalBiaya = 0.0;
        }

        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + tambahanVip);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + totalBiaya);
        System.out.println("============================================");

        if (isSapiSpesial) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
    }
}

class Spa extends PerawatanSapi {
    Spa(String nama, int berat, String layanan, String kelas) {
        super(nama, berat, layanan, kelas);
    }

    @Override
    double getHargaPerKg() {
        return 8000;
    }
}

class PotongKuku extends PerawatanSapi {
    PotongKuku(String nama, int berat, String layanan, String kelas) {
        super(nama, berat, layanan, kelas);
    }

    @Override
    double getHargaPerKg() {
        return 6000;
    }
}

class Grooming extends PerawatanSapi {
    Grooming(String nama, int berat, String layanan, String kelas) {
        super(nama, berat, layanan, kelas);
    }

    @Override
    double getHargaPerKg() {
        return 10000;
    }
}

public class Hardmoo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nama;
        while (true) {
            nama = scanner.nextLine();
            if (nama.matches("^[a-zA-Z]+$"))
                break;
            System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
        }

        int berat = 0;
        while (true) {
            try {
                berat = Integer.parseInt(scanner.nextLine());
                if (berat >= 1 && berat <= 80)
                    break;
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            } catch (Exception e) {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
            }
        }

        String layanan;
        while (true) {
            layanan = scanner.nextLine();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming"))
                break;
            System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
        }

        String kelas;
        while (true) {
            kelas = scanner.nextLine();
            if (kelas.equals("reguler") || kelas.equals("vip"))
                break;
            System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
        }

        PerawatanSapi sapi = null;

        if (layanan.equals("spa")) {
            sapi = new Spa(nama, berat, layanan, kelas);
        } else if (layanan.equals("potong_kuku")) {
            sapi = new PotongKuku(nama, berat, layanan, kelas);
        } else if (layanan.equals("grooming")) {
            sapi = new Grooming(nama, berat, layanan, kelas);
        }

        if (sapi != null) {
            sapi.cetakNota();
        }

        scanner.close();
    }
}