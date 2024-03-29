package com.lyricxinc.lyricx.service.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public enum S3BucketFoldersType {
        ARTIST_FOLDER {
            @Override
            public String toString() {
                return "artist";
            }
        },

        ALBUM_FOLDER {
            @Override
            public String toString() {
                return "album";
            }
        },

        SONG_FOLDER {
            @Override
            public String toString() {
                return "song";
            }
        },

        CONTRIBUTOR_FOLDER {
            @Override
            public String toString() {
                return "contributor";
            }
        }
    }

    public String uploadFile(MultipartFile multipartFile, S3BucketFoldersType s3BucketFolder) {

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
        //        return s3BucketFolder + "/" + fileName;

        if (S3BucketFoldersType.SONG_FOLDER.equals(s3BucketFolder))
            return "SGPZN5J.jpg";
        else if (S3BucketFoldersType.ARTIST_FOLDER.equals(s3BucketFolder))
            return "55wMdCh.jpg";
        else if (S3BucketFoldersType.ALBUM_FOLDER.equals(s3BucketFolder))
            return "9jwBbem.jpg";
        else
            return "ra6Vqzm.jpg";
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private void uploadFileTos3bucket(String fileName, S3BucketFoldersType s3BucketFolder, File file) {

        s3client.putObject(new PutObjectRequest(bucketName, s3BucketFolder + "/" + fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String deleteFileFromS3Bucket(String fileUrl, S3BucketFoldersType s3BucketFolder) {

        //TODO uncomment when AWS account is acquired
        // String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        // s3client.deleteObject(new DeleteObjectRequest(bucketName, s3BucketFolder + "/" + fileName));
        return "Successfully deleted";
    }

}
