package my.examples.springjdbc.dto;

import lombok.ToString;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@ToString
public class Criteria {
    private int page;
    private int perPageNum;

    public Criteria(){
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {

        if(perPageNum <= 0) {
            this.perPageNum = 10;
            return;
        }

        this.perPageNum = perPageNum;
    }

    public int getPageStart() {
        return (this.page -1) * perPageNum;
    }
}
