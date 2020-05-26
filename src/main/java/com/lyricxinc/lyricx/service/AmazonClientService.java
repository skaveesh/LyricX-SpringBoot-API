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
import com.lyricxinc.lyricx.core.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@Service
public class AmazonClientService {

    private AmazonS3 s3client;

    @Value("${com.amazonaws.s3.amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${com.amazonaws.s3.amazonProperties.bucketName}")
    private String bucketName;

    @Value("${com.amazonaws.s3.amazonProperties.accessKey}")
    private String accessKey;

    @Value("${com.amazonaws.s3.amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {

        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

    public enum S3BucketFolders {
        ARTIST_FOLDER
                {
                    @Override
                    public String toString() {

                        return "artist";
                    }
                },

        ALBUM_FOLDER
                {
                    @Override
                    public String toString() {

                        return "album";
                    }
                },

        SONG_FOLDER
                {
                    @Override
                    public String toString() {

                        return "song";
                    }
                },

        CONTRIBUTOR_FOLDER
                {
                    @Override
                    public String toString() {

                        return "contributor";
                    }
                }
    }

    public String uploadFile(MultipartFile multipartFile, S3BucketFolders s3BucketFolder) {

        String fileUrl;
        //TODO uncomment when AWS account is acquired
        //        try
        //        {
        //            File file = convertMultiPartToFile(multipartFile);
        //            String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
        //            fileUrl = endpointUrl + "/" + bucketName + "/" + s3BucketFolder + "/" + fileName;
        //            uploadFileTos3bucket(fileName, s3BucketFolder, file);
        //            file.delete();
        //        } catch (Exception e)
        //        {
        //            e.printStackTrace();
        //            throw new FileUploadException(ErrorMessage.LYRICX_ERR_13, ErrorCode.LYRICX_ERR_13);
        //        }
        //        return fileUrl;

        if(S3BucketFolders.SONG_FOLDER.equals(s3BucketFolder))
            return "https://i.imgur.com/L1jxnoA.png";
        else if(S3BucketFolders.ARTIST_FOLDER.equals(s3BucketFolder))
            return "https://i.imgur.com/BhuLwuq.jpg";
        else if(S3BucketFolders.ALBUM_FOLDER.equals(s3BucketFolder))
            return "https://i.imgur.com/6N9NtBs.jpg";
        else
            return "https://i.imgur.com/koV4MZK.png";
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

        //TODO uncomment when AWS account is acquired
        // String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        // s3client.deleteObject(new DeleteObjectRequest(bucketName, s3BucketFolder + "/" + fileName));
        return "Successfully deleted";
    }

}
