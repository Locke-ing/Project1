package app;

import util.PinyinUtil;
import util.Util;

import java.io.File;
import java.util.Date;
import java.util.Objects;

public class FileMeta {
    // 文件名称
    private String name;
    // 文件所在的父目录的路径
    private String path;
    // 文件大小
    private Long size;
    // 文件上次修改时间
    private Date lastModified;
    // 是否是文件夹
    private Boolean isDirectory;
    // 给客户端控件使用，和app.fxml中定义的名称要一致
    private String sizeText;
    // 和app.fxml定义的一致
    private String lastModifiedText;
    // 文件名拼音
    private String pinyin;
    // 文件名拼音首字母
    private String pinyinFirst;
    // 通过文件设置属性
    public FileMeta(File file){
        this(file.getName(), file.getParent(), file.isDirectory(), file.length(),
                new Date(file.lastModified()));
    }
    // 通过数据库获取的数据设置FileMeta
    public FileMeta(String name, String path, Boolean isDirectory, long size,
                    Date lastModified){
        this.name = name;
        this.path = path;
        this.isDirectory = isDirectory;
        this.size = size;
        this.lastModified = lastModified;
        if(PinyinUtil.containsChinese(name)){
            String[] pinyins = PinyinUtil.get(name);
            pinyin = pinyins[0];
            pinyinFirst = pinyins[1];
        }
        // 客户端表格控件文件大小，文件上次修改时间的设置
        sizeText = Util.parseSize(size);
        lastModifiedText = Util.parseDate(lastModified);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMeta meta = (FileMeta) o;
        return Objects.equals(name, meta.name) &&
                Objects.equals(path, meta.path) &&
                Objects.equals(isDirectory, meta.isDirectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, path, isDirectory);
    }

    @Override
    public String toString() {
        return "FileMeta{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", isDirectory=" + isDirectory +
                '}';
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Long getSize() {
        return size;
    }

    public String getLastModifiedText() {
        return lastModifiedText;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getPinyinFirst() {
        return pinyinFirst;
    }
}
