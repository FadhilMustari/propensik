package asik.propensik.trainnas.model;

import java.io.Serializable;

public class PenghitungBmi implements Serializable {
    private Double beratBadan;
    private Double tinggiBadan;
    private String kategori;

    public PenghitungBmi(String beratBadan, String tinggiBadan) {
        this.beratBadan = Double.parseDouble((beratBadan));
        this.tinggiBadan = Double.parseDouble((tinggiBadan)) / 100;
    }

    public String hitungBmi() {
        double hasilBmi = beratBadan / (Math.pow(tinggiBadan, 2));
        if (hasilBmi < 18.5) {
            this.kategori = "Berat badan anda kurang";
        } else if (hasilBmi >= 18.5 && hasilBmi <= 22.9) {
            this.kategori = "Berat badan anda normal";
        } else if (hasilBmi > 22.9 && hasilBmi <= 29.9) {
            this.kategori = "Berat badan anda berlebih (kecenderungan obesitas)";
        } else {
            this.kategori = "Anda obesitas";
        }

        return String.format("%.2f", hasilBmi);
    }

    public Double getWeight() {
        return beratBadan;
    }

    public Double getHeight() {
        return tinggiBadan;
    }

    public String getKategori() {
        return kategori;
    }
}

