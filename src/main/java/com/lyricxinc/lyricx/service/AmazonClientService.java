package com.lyricxinc.lyricx.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lyricxinc.lyricx.core.exception.FileUploadErrorCustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class AmazonClientService {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {

        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

    public enum S3BucketFolders {
        ARTIST_FOLDER
                {
                    public String toString() {

                        return "artist";
                    }
                },

        ALBUM_FOLDER
                {
                    public String toString() {

                        return "album";
                    }
                },

        SONG_FOLDER
                {
                    public String toString() {

                        return "song";
                    }
                },

        CONTRIBUTOR_FOLDER
                {
                    public String toString() {

                        return "contributor";
                    }
                }
    }

    public String uploadFile(MultipartFile multipartFile, S3BucketFolders s3BucketFolder) {

        String fileUrl;

        try
        {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            fileUrl = endpointUrl + "/" + bucketName + "/" + s3BucketFolder + "/" + fileName;
            uploadFileTos3bucket(fileName, s3BucketFolder, file);
            file.delete();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new FileUploadErrorCustomException("Error while uploading the file. Try again.");
        }

        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private void uploadFileTos3bucket(String fileName, S3BucketFolders s3BucketFolder, File file) {

        s3client.putObject(new PutObjectRequest(bucketName, s3BucketFolder + "/" + fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl, S3BucketFolders s3BucketFolder) {

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(bucketName, s3BucketFolder + "/" + fileName));
        return "Successfully deleted";
    }

}
