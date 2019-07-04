package org.fisco.bcos.Bean;

public class PetsListItem {
            private int petId;
            private String petType;
            private int petPrice;
            private String petName;
            private int petStatus;
            private String petImg;
            private String petIntro;

    public PetsListItem() {
    }

    public PetsListItem(int petId, String petType, int petPrice, String petName, int petStatus, String petImg, String petIntro) {
        this.petId = petId;
        this.petType = petType;
        this.petPrice = petPrice;
        this.petName = petName;
        this.petStatus = petStatus;
        this.petImg = petImg;
        this.petIntro = petIntro;
    }


    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getPetPrice() {
        return petPrice;
    }

    public void setPetPrice(int petPrice) {
        this.petPrice = petPrice;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(int petStatus) {
        this.petStatus = petStatus;
    }

    public String getPetImg() {
        return petImg;
    }

    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }

    public String getPetIntro() {
        return petIntro;
    }

    public void setPetIntro(String petIntro) {
        this.petIntro = petIntro;
    }
}
