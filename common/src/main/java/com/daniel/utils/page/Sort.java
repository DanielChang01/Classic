package com.daniel.utils.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * Created by danielchang on 2018/6/24.
 */
public class Sort implements Serializable{
    public static final Sort.Direction DEFAULT_DIRECTION;
    private static final long serialVersionUID = -4717615485715830276L;
    private List<Order> orders;

    protected Sort() {
        this((List)(new ArrayList(0)));
    }

    public Sort(Sort.Order... orders) {
        this(Arrays.asList(orders));
    }

    @JsonCreator
    public Sort(@JsonProperty("orders") List<Sort.Order> orders) {
        Assert.notNull(orders, "You have to provide sort property to sort by!");
        this.orders = orders;
    }

    public List<Sort.Order> getOrders() {
        return this.orders;
    }

    public Sort.Order getOrderByProperty(String property) {
        Iterator var2 = this.orders.iterator();

        Sort.Order order;
        do {
            if(!var2.hasNext()) {
                return null;
            }

            order = (Sort.Order)var2.next();
        } while(!order.getProperty().equals(property));

        return order;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Sort)) {
            return false;
        } else {
            Sort sort = (Sort)o;
            return this.orders != null?this.orders.equals(sort.orders):sort.orders == null;
        }
    }

    public int hashCode() {
        return this.orders != null?this.orders.hashCode():0;
    }

    public String toString() {
        return "Sort{orders=" + this.orders + '}';
    }

    static {
        DEFAULT_DIRECTION = Sort.Direction.ASC;
    }

    public static class Order implements Serializable {
        private static final long serialVersionUID = -1212865797767706224L;
        private Sort.Direction direction;
        private String property;

        protected Order() {
            this.direction = Sort.DEFAULT_DIRECTION;
        }

        public Order(String property) {
            this((Sort.Direction)null, property);
        }

        @JsonCreator
        public Order(@JsonProperty("direction") Sort.Direction direction, @JsonProperty("property") String property) {
            this.direction = Sort.DEFAULT_DIRECTION;
            if(direction != null) {
                this.direction = direction;
            }

            this.property = property;
        }

        public static List<Sort.Order> create(Sort.Direction direction, Collection<String> properties) {
            List<Sort.Order> orders = Lists.newArrayListWithCapacity(properties.size());
            Iterator var3 = properties.iterator();

            while(var3.hasNext()) {
                String property = (String)var3.next();
                orders.add(new Sort.Order(direction, property));
            }

            return orders;
        }

        public Sort.Direction getDirection() {
            return this.direction;
        }

        public String getProperty() {
            return this.property;
        }

        @JsonIgnore
        public boolean isAscending() {
            return this.direction.equals(Sort.Direction.ASC);
        }

        public boolean equals(Object o) {
            if(this == o) {
                return true;
            } else if(!(o instanceof Sort.Order)) {
                return false;
            } else {
                Sort.Order order = (Sort.Order)o;
                return this.direction != order.direction?false:this.property.equals(order.property);
            }
        }

        public int hashCode() {
            int result = this.direction.hashCode();
            result = 31 * result + this.property.hashCode();
            return result;
        }

        public String toString() {
            return "Order{direction=" + this.direction + ", property='" + this.property + '\'' + '}';
        }
    }

    public static enum Direction {
        ASC,
        DESC;

        private Direction() {
        }

        public static Sort.Direction fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception var2) {
                throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", new Object[]{value}), var2);
            }
        }

        public static Sort.Direction fromStringOrNull(String value) {
            try {
                return fromString(value);
            } catch (IllegalArgumentException var2) {
                return null;
            }
        }
    }
}

