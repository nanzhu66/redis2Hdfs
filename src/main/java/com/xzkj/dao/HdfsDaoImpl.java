package com.xzkj.dao;

import com.xzkj.utills.MyHdfsUtills;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class HdfsDaoImpl implements HdfsDao {

    private static Logger rollingLogger = LoggerFactory.getLogger(HdfsDaoImpl.class);

    private FileSystem fs = null;

    public HdfsDaoImpl() {
        this.fs = MyHdfsUtills.getFileSystem();
    }

    /**
     * 向hdfs的指定路径追加数据，如果路径不存在则会创建新文件
     *
     * @param hdfs_path hdfs的路径
     * @param data      要存入的内容
     */
    @Override
    public void write(String hdfs_path, String data) {

        FSDataOutputStream out = null;
        try {
            Path path = new Path(hdfs_path);
            // 判断文件路径是否存在
            boolean flag = fs.exists(path);
            if (!flag) {
                fs.mkdirs(path.getParent());
                out = fs.create(path);
            } else {
                // 获取该路径的输出流，向文件追加数据
                out = fs.append(path);
            }
            out.write(data.getBytes());
        } catch (IOException e) {
            rollingLogger.error("", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                rollingLogger.error("", e);
            }
        }
    }

    /**
     * 关闭FileSystem
     */
    @Override
    public void close() {
        try {
            fs.close();
        } catch (IOException e) {
            rollingLogger.error("", e);
        }
    }

}
