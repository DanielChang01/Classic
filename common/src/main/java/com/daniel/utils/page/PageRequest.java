package com.daniel.utils.page;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by danielchang on 2018/6/24.
 */
public class PageRequest implements Serializable{
    private PageRequest.Page page = new PageRequest.Page(15, 1, (Sort)null);

    public PageRequest() {
    }

    public PageRequest.Page getPage() {
        return this.page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }

    @JsonIgnore
    public int getOffset() {
        if(this.page == null) {
            return 0;
        } else {
            int tmp = (this.page.pageNo - 1) * this.page.pageSize;
            return tmp < 0?0:tmp;
        }
    }

    @JsonIgnore
    public int getLimit() {
        return this.page == null?0:this.page.pageSize;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof PageRequest)) {
            return false;
        } else {
            PageRequest that = (PageRequest)o;
            return this.page != null?this.page.equals(that.page):that.page == null;
        }
    }

    public int hashCode() {
        return this.page != null?this.page.hashCode():0;
    }

    public String toString() {
        return "PageRequest{page=" + this.page + '}';
    }

    public static class Page implements Serializable {
        private static final long serialVersionUID = -9116229816861557536L;
        int pageSize;
        int pageNo;
        Sort sort;

        public Page() {
            this.pageSize = 15;
            this.pageNo = 1;
        }

        public Page(int pageSize, int pageNo) {
            this(pageSize, pageNo, (Sort)null);
        }

        public Page(int pageSize, int pageNo, Sort sort) {
            this.pageSize = 15;
            this.pageNo = 1;
            this.pageSize = pageSize;
            this.pageNo = pageNo;
            this.sort = sort;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        @JsonIgnore
        public Limit getLimit() {
            return Limit.createByPage(this.pageNo, this.pageSize);
        }

        @JsonIgnore
        public void setLimit(Limit limit) {
            this.pageNo = limit.getPage();
            this.pageSize = limit.getSize();
        }

        public int getPageNo() {
            return this.pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public Sort getSort() {
            return this.sort;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        public boolean equals(Object o) {
            if(this == o) {
                return true;
            } else if(!(o instanceof PageRequest.Page)) {
                return false;
            } else {
                PageRequest.Page page = (PageRequest.Page)o;
                return this.pageSize != page.pageSize?false:(this.pageNo != page.pageNo?false:(this.sort != null?this.sort.equals(page.sort):page.sort == null));
            }
        }

        public int hashCode() {
            int result = this.pageSize;
            result = 31 * result + this.pageNo;
            result = 31 * result + (this.sort != null?this.sort.hashCode():0);
            return result;
        }

        public String toString() {
            return "Page{pageSize=" + this.pageSize + ", pageNo=" + this.pageNo + ", sort=" + this.sort + '}';
        }
    }
}

