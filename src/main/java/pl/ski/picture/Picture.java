package pl.ski.picture;

import org.springframework.web.multipart.MultipartFile;
import pl.ski.company.Company;
import pl.ski.management_file.ManagementFile;
import pl.ski.offer_ski.OfferSki;
import pl.ski.static_values.ServerPHPToImg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String addres;

    public Picture() {
    }

    public Picture(@NotNull String addres) {
        this.addres = addres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    private String saveImage(String typeImag, String user, MultipartFile file) throws IOException {
        String result = "";
        String currentPath = ManagementFile.addToPath(ServerPHPToImg.getMainDirectory(), ServerPHPToImg.MAIN_SERVER_PHP_DIRECTORY);
        currentPath = ManagementFile.addToPath(currentPath, ServerPHPToImg.IMG_DIRECTORY);
        if(ManagementFile.createDirectory(currentPath, typeImag)){
            currentPath = ManagementFile.addToPath(currentPath, typeImag);
            if(ManagementFile.createDirectory(currentPath, user)){
                currentPath = ManagementFile.addToPath(currentPath, user);
               String nameFile = user + "-" + (new SimpleDateFormat("ddMMyyyyHHmmss")).format(new Date()) + "." + file.getContentType().split("/" )[1];
                if(ManagementFile.createFile(currentPath, nameFile)){
                    currentPath = ManagementFile.fullPathFile(currentPath, nameFile);
                    if(ManagementFile.writeByte(file.getBytes(), new File(currentPath))) {
                        result = "http://" + ServerPHPToImg.BASIC_ADDRESS_URL + "/" + ServerPHPToImg.MAIN_SERVER_PHP_DIRECTORY + "/" + ServerPHPToImg.IMG_DIRECTORY + "/" + typeImag + "/" + user + "/" + nameFile;
                    }
                }
            }
        }
        return result;
    }

    public Picture uploadImageProfileCompany(MultipartFile file, Company company){
        Picture result = new Picture();
        try {
            result.setAddres(this.saveImage(ServerPHPToImg.TYPE_PICTURE_COMPANY, company.getId().toString(), file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Picture uploadImageOfferSki(MultipartFile file, OfferSki offerSki) {
        Picture result = new Picture();
        try {
            result.setAddres(this.saveImage(ServerPHPToImg.TYPE_PICTURE_OFFERSKI, offerSki.getId().toString(), file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
