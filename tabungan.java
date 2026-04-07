package Latsol_UTS;

import java.util.*;

abstract class Student {
    String nama, tipe;
    int saldo = 0;

    Student(String nama, String tipe) {
        this.nama = nama;
        this.tipe = tipe;
    }

    public void save(int jml) {
        saldo += jml;
        System.out.println("Saldo " + nama + ": " + saldo);
    }

    abstract int hitungBiaya(int jml);

    public void take(int jml) {
        int biaya = hitungBiaya(jml);
        if (saldo >= biaya) {
            saldo -= biaya;
            System.out.println("Saldo " + nama + ": " + saldo);
        } else {
            System.out.println("Saldo " + nama + " tidak cukup");
        }
    }

    public void check() {
        System.out.println(nama + " | " + tipe + " | saldo: " + saldo);
    }
}

class Reguler extends Student {
    Reguler(String n) {
        super(n, "REGULER");
    }

    @Override
    int hitungBiaya(int jml) {
        return jml;
    }
}

class Beasiswa extends Student {
    Beasiswa(String n) {
        super(n, "BEASISWA");
    }

    @Override
    int hitungBiaya(int jml) {
        return Math.max(0, jml - 1000);
    }
}

public class tabungan {
    public static void main(String[] args) {
        Scanner lol = new Scanner(System.in);
        Map<String, Student> data = new HashMap<>();

        if (!lol.hasNextInt())
            return;
        int n = lol.nextInt();

        while (n-- > 0) {
            String cmd = lol.next();
            if (cmd.equals("CREATE")) {
                String tipe = lol.next(), nama = lol.next();
                if (data.containsKey(nama))
                    System.out.println("Akun sudah terdaftar");
                else {
                    data.put(nama, tipe.equals("REGULER") ? new Reguler(nama) : new Beasiswa(nama));
                    System.out.println(tipe + " " + nama + " berhasil dibuat");
                }
            } else {
                String nama = lol.next();
                Student s = data.get(nama);
                if (s == null) {
                    System.out.println("Akun tidak ditemukan");
                    if (!cmd.equals("CHECK"))
                        lol.next();
                } else if (cmd.equals("SAVE"))
                    s.save(lol.nextInt());
                else if (cmd.equals("TAKE"))
                    s.take(lol.nextInt());
                else if (cmd.equals("CHECK"))
                    s.check();
            }
        }
    }
}
