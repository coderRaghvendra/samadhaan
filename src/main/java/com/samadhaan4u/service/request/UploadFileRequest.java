package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.service.response.Response;
import com.samadhaan4u.service.response.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;

/**
 * Created by raghvendra.mishra on 16/03/18.
 */
public class UploadFileRequest extends AbstractRequest {
    public static final String DOCUMENT_UPLOAD_LOCATION = "/Users/raghvendra.mishra/IdeaProjects/" +
            "samadhaan/src/main/webapp/resources/document/";
    private static final Logger logger = LoggerFactory.getLogger(UploadFileRequest.class);
    private MultipartFile file;

    public UploadFileRequest(){super();}
    protected UploadFileRequest(Builder builder){
        super(builder);
        this.file = builder.file;

    }

    public static class Builder extends AbstractRequest.Builder<UploadFileRequest, Builder>{

        private MultipartFile file;

        public Builder() {
            super();
        }

        @Override
        public UploadFileRequest build(){ return new UploadFileRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder file(MultipartFile file) {
            this.file = file;
            return this;
        }
    }

    public Response process(){
        logger.info("in uoload file request, userid = " + this.userId);
        UploadFileResponse.Builder ufBuilder = new UploadFileResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        Result result;
        try{
            String UPLOAD_LOCATION = "";
            String timestamp = (new Timestamp(System.currentTimeMillis())).toString()
                    .replaceAll(" ", "_");
            String newFileName = userId + "_" + timestamp + "_" + file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(DOCUMENT_UPLOAD_LOCATION + newFileName));
            Document.Builder dBuilder = new Document.Builder();
            DaoManager.documentDao().insert(dBuilder.name(newFileName).description(file.getOriginalFilename())
                    .userId(this.userId).path(DOCUMENT_UPLOAD_LOCATION).build());
            result = rBuilder.success(true).message(ResponseMessage.FILE_UPLOADED).build();
        } catch(Exception e){
            result = rBuilder.success(false).message(ResponseMessage.INTERNAL_ERROR).build();
            e.printStackTrace();
        }
        return ufBuilder.result(result).build();
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
