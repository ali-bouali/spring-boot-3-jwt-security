package com.herookie.employee.utils;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CommonsLog
public class SearchPagination<T> implements Serializable {

    private static final long serialVersionUID = 327622465297364945L;
    private T seek;
    private int page;
    private int records;
    private String order;
    private String direction;

    /**
     * @param filter
     * @param page
     * @param records
     */
    public SearchPagination(T filter, int page, int records) {
        super();
        this.seek = filter;
        this.page = page;
        this.records = records;
    }

    public int getPage() {
        return this.page - 1;
    }

    public Direction getDirection() {
        if (this.direction != null && !this.direction.isEmpty()) {
            return Direction.fromString(this.direction);
        }
        return Direction.ASC;
    }

    public PageRequest getPageRequest() {
        try {
            if (this.direction == null || this.order == null || (this.order != null && this.order.length() == 0)) {
                return PageRequest.of(this.getPage(), this.getRecords());
            } else {
                return PageRequest.of(this.getPage(), this.getRecords(), this.getDirection(), this.order);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

}