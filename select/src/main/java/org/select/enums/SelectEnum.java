package org.select.enums;

public enum SelectEnum {
    GROUPCREATESUCCESS(3,"组创建成功"),
    GROUPCREATEFAILURE(4,"组创建失败"),
    GROUPDELETEDSUCCESS(5,"组删除成功"),
    FROUPDELETEFAILURE(6,"组删除失败");
    private int state;
    private String info;

    SelectEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }
}
