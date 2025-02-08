package learn;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Dotenv env = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(env.get("CLOUDINARY_URL"));
        Map config = ObjectUtils.asMap(
            "use_filename", true,
                "unique_filename", true,
                "overwrite", true,
                "transformation", new Transformation<>().width(400).height(400).crop("pad").fetchFormat("avif")
        );
        CompletableFuture<Map> uploaded = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Start upload...");
                Map response = cloudinary.uploader().upload(new File("C:\\Users\\ADMIN\\Downloads\\dog_sit.jpg"), config);
                System.out.println("Upload successful!");
                return response;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Map response = uploaded.get();
        pic p = new pic();
        p.setDateCreated(String.valueOf(response.get("created_at")));
        p.setUrlImage(String.valueOf(response.get("url")));
        p.display();
    }
}