package com.jocata.learning.EmployeeMgmt.model;
    public class Employee {
        private int eid;
        private String ename;
        private int esal;

        public int getId() {
            return eid;
        }

        public void setId(int id) {
            this.eid = id;
        }

        public String getName() {
            return ename;
        }

        public void setName(String firstName) {
            this.ename = firstName;
        }

        public int getSalary() {
            return esal;
        }

        public void setSalary(int sal) {
            this.esal = sal;
        }
    }

       /* @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Employee)) return false;

            Employee otherObj = (Employee) obj;
            return (this.eid == otherObj.getId());
        }

        @Override
        public String toString() {
            return "ID: " + getId() + ", Name: " + getName() + ", Salary: " + getSalary();
        }

    }*/



