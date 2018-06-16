package com.samadhaan4u.service.request;

import com.samadhaan4u.dto.Result;
import com.samadhaan4u.dto.constant.ResponseMessage;
import com.samadhaan4u.model.dao.DaoManager;
import com.samadhaan4u.model.entity.Document;
import com.samadhaan4u.service.response.DownloadFileResponse;
import com.samadhaan4u.service.response.Response;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by raghvendra.mishra on 10/06/18.
 */
public class DownloadFileRequest extends AbstractRequest {
    public static final String DOCUMENT_DOWNLOAD_LOCATION = "/Users/raghvendra.mishra/IdeaProjects/" +
            "samadhaan/src/main/webapp/resources/document/";
    private static final Logger logger = LoggerFactory.getLogger(DownloadFileRequest.class);
    private long id;
    private HttpServletResponse response;

    public DownloadFileRequest(){ super();}

    protected DownloadFileRequest(Builder builder){
        super(builder);
        this.id = builder.id;
        this.response = builder.response;
    }

    public static class Builder extends AbstractRequest.Builder<DownloadFileRequest, Builder>{
        private long id;
        private HttpServletResponse response;

        public Builder() {
            super();
        }

        @Override
        public DownloadFileRequest build(){ return new DownloadFileRequest(this);}

        @Override
        public Builder self() {
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder response(HttpServletResponse response) {
            this.response = response;
            return this;
        }
    }

    public Response process(){
        logger.info("DownloadFileRequest received, being processed.");
        DownloadFileResponse.Builder dfrBuilder = new DownloadFileResponse.Builder();
        Result.Builder rBuilder = new Result.Builder();
        boolean success = false;
        try {
            Document document = DaoManager.documentDao().get(id).orElse(null);
            if(document != null && document.getUserId() == this.userId){
                Path file = Paths.get(DOCUMENT_DOWNLOAD_LOCATION, document.getName());
                if (Files.exists(file)) {
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.addHeader("Content-Disposition", "attachment; filename=" + document.getName());
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();

                }
//                InputStream is = new FileInputStream(filePath);
//                IOUtils.copy(is, response.getOutputStream());
//                response.setContentType("application/pdf");
//                response.addHeader("Content-Disposition", "attachment; filename="+document.getName());
//                response.flushBuffer();
                success = true;
                rBuilder.message(ResponseMessage.NO_ERROR);
                logger.info("DownloadFileRequest processed successfully.");
            } else if(document == null){
                rBuilder.message(ResponseMessage.NOT_FOUND);
                logger.info("No document found for this document id = {}.", id);
            } else if(document.getUserId() == this.userId){
                rBuilder.message(ResponseMessage.PERMISSION_DENIED);
                logger.info("You are not authorized to download this file.");
            }
        } catch(Exception e){
            rBuilder.message(ResponseMessage.EXCEPTION_OCCURRED);
            logger.info("Exception occurred while processing DownloadFileRequest for user id = {} and document " +
                    "id = {}.", this.userId, this.id);
            e.printStackTrace();
        }
        return dfrBuilder.userId(this.userId).result(rBuilder.success(success).build()).build();
    }

    public long getId() {
        return id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}