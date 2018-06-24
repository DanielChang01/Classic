package com.daniel.utils.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielchang on 2018/6/24.
 */
public class Pager<T> implements Serializable {
    private Pager.PageData page;
    private List<T> data;

    public Pager() {
    }

    @JsonCreator
    public Pager(@JsonProperty("page") Pager.PageData page, @JsonProperty("data") List<T> data) {
        this.page = page;
        this.data = Lists.newArrayList();
        this.data.addAll(data);
    }

    public static <T> Pager.Builder<T> builder(List<T> data) {
        return (new Pager.Builder()).data(data);
    }

    public Pager.PageData getPage() {
        return this.page;
    }

    public List<T> getData() {
        return (List)(this.data == null?Lists.newArrayList():this.data);
    }

    public boolean hasData() {
        return this.data != null && !this.data.isEmpty();
    }

    public int getDataSize() {
        return this.data == null?0:this.data.size();
    }

    public <E> Pager<E> transform(Function<T, E> function) {
        return new Pager(this.page, Lists.transform(this.data, function));
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Pager)) {
            return false;
        } else {
            Pager<?> pager = (Pager)o;
            if(this.page != null) {
                if(this.page.equals(pager.page)) {
                    return this.data != null?this.data.equals(pager.data):pager.data == null;
                }
            } else if(pager.page == null) {
                return this.data != null?this.data.equals(pager.data):pager.data == null;
            }

            return false;
        }
    }

    public int hashCode() {
        int result = this.page != null?this.page.hashCode():0;
        result = 31 * result + (this.data != null?this.data.hashCode():0);
        return result;
    }

    public String toString() {
        return "Pager{page=" + this.page + ", data=" + this.data + '}';
    }

    public static class PageData implements Serializable {
        private static final long serialVersionUID = 3599580483667456581L;
        private int curPage;
        private int pageSize;
        private int totalSize;
        private Sort sort;

        public PageData() {
        }

        public PageData(int curPage, int pageSize, int totalSize) {
            this.curPage = curPage;
            this.pageSize = pageSize;
            this.totalSize = totalSize;
        }

        @JsonCreator
        public PageData(@JsonProperty("curPage") int curPage, @JsonProperty("pageSize") int pageSize, @JsonProperty("sort") Sort sort, @JsonProperty("totalSize") int totalSize) {
            this.curPage = curPage;
            this.pageSize = pageSize;
            this.sort = sort;
            this.totalSize = totalSize;
        }

        public PageData(PageRequest.Page page, int totalSize) {
            if(page != null) {
                this.curPage = page.getPageNo();
                this.pageSize = page.getPageSize();
                this.sort = page.getSort();
            }

            this.totalSize = totalSize;
        }

        public int getCurPage() {
            return this.curPage;
        }

        public boolean hasPreviousPage() {
            return this.getCurPage() > 1;
        }

        public boolean isFirstPage() {
            return !this.hasPreviousPage();
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public int getTotalSize() {
            return this.totalSize;
        }

        public boolean hasNextPage() {
            return this.getCurPage() < this.getTotalPage();
        }

        public boolean isLastPage() {
            return !this.hasNextPage();
        }

        public int getTotalPage() {
            return this.getPageSize() == 0?1:(int)Math.ceil((double)this.totalSize / (double)this.getPageSize());
        }

        public Sort getSort() {
            return this.sort;
        }

        public boolean equals(Object o) {
            if(this == o) {
                return true;
            } else if(!(o instanceof Pager.PageData)) {
                return false;
            } else {
                Pager.PageData pageData = (Pager.PageData)o;
                return this.curPage != pageData.curPage?false:(this.pageSize != pageData.pageSize?false:(this.totalSize != pageData.totalSize?false:(this.sort != null?this.sort.equals(pageData.sort):pageData.sort == null)));
            }
        }

        public int hashCode() {
            int result = this.curPage;
            result = 31 * result + this.pageSize;
            result = 31 * result + this.totalSize;
            result = 31 * result + (this.sort != null?this.sort.hashCode():0);
            return result;
        }

        public String toString() {
            return "PageData{curPage=" + this.curPage + ", pageSize=" + this.pageSize + ", totalSize=" + this.totalSize + ", sort=" + this.sort + '}';
        }
    }

    public static class Builder<T> {
        private List<T> data;
        private PageRequest.Page page;
        private int totalSize;

        private Builder() {
            this.totalSize = 0;
        }

        public Pager.Builder<T> current(PageRequest.Page page) {
            this.page = page;
            return this;
        }

        public Pager.Builder<T> total(int totalSize) {
            this.totalSize = totalSize;
            return this;
        }

        public Pager.Builder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public Pager<T> create() {
            return new Pager(new Pager.PageData(this.page, this.totalSize), this.data);
        }
    }
}
