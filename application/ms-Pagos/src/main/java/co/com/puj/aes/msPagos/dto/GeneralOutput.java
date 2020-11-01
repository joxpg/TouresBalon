package co.com.puj.aes.msPagos.dto;

import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.Map;

public class GeneralOutput extends AbstractOutput {
    private Object payLoad;
    private Map<String,Object> page;

    public GeneralOutput(int status, String message,Object payLoad) {
        super.setStatus(status);
        super.setMessage(message);
        this.payLoad = payLoad;
    }

    public GeneralOutput(int status, String message,Object payLoad, Page pages) {
        super.setStatus(status);
        super.setMessage(message);
        this.payLoad = payLoad;
        Map<String, Object> dataPage = new LinkedHashMap<>();
        dataPage.put("totalElements",pages.getTotalElements());
        dataPage.put("totalPages",pages.getTotalPages());
        this.page = dataPage;
    }

    public GeneralOutput(int status, String message,Page pages) {
        super.setStatus(status);
        super.setMessage(message);
        this.payLoad = pages.get();
        Map<String, Object> dataPage = new LinkedHashMap<>();
        dataPage.put("totalElements",pages.getTotalElements());
        dataPage.put("totalPages",pages.getTotalPages());
        this.page = dataPage;
    }

    public Object getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(Object payLoad) {
        this.payLoad = payLoad;
    }

    public Map<String, Object> getPage() {
        return page;
    }

    public void setPage(Map<String, Object> page) {
        this.page = page;
    }

}
