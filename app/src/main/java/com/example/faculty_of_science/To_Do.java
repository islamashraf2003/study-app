package com.example.faculty_of_science;

public class To_Do
{
    String name;
   private boolean isChecked;

    public To_Do(String name) {
        this.name = name;
        this.isChecked=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "To_Do{" +
                "name='" + name + '\'' +

                '}';
    }
}
