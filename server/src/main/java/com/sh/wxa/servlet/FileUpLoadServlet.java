package com.sh.wxa.servlet;

import com.sh.wxa.Server;
import com.sh.wxa.constants.AppConstants;
import com.sh.wxa.constants.UpLoadType;
import com.sh.wxa.util.StringUtils;
import com.sh.wxa.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Slf4j
public class FileUpLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String imageUrl = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");

            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(req)) {
                return;
            }

            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(req);
            //创建保存文件路径
            final String ymd = TimeUtil.getYMDTimeString(new Date());
            UpLoadType upLoadType = UpLoadType.valueOf(Integer.parseInt(list.get(0).getString("UTF-8")));
            final String savePath = getTopicImageLocalSavePath(ymd, upLoadType);

            //上传文件
            FileItem imageFile = list.get(1);
            String filename = getFileName(imageFile);
            if (filename != null) {
                readFile(savePath, imageFile, filename);
                imageUrl = getTopicImageRemoteSavePath(ymd, upLoadType) + "/" + filename;
                System.out.println("image:" + imageUrl);
            }

//            for (FileItem item : list) {
//                if (item.isFormField()) {
//                    //普通数据
//                    String name = item.getFieldName();
//                    //解决普通输入项的数据的中文乱码问题
//                    String value = item.getString("UTF-8");
//                    System.out.println(name + "=" + value);
//                } else {
//                    //上传文件
//                    String filename = getFileName(item);
//                    if (filename == null) continue;
//                    readFile(savePath, item, filename);
//                    imageUrl = getTopicImageRemoteSavePath(ymd) + "/" + filename;
//                    System.out.println("image:" + imageUrl);
//                }
//            }
            resp.setHeader("Cache-Control", "no-cache");
            resp.getOutputStream().write(imageUrl.getBytes(AppConstants.CHARSET_UTF8));
        } catch (Exception e) {
            log.error("upload error", e);
        }
    }

    //得到上传的文件名
    private String getFileName(FileItem item) {
        String filename = item.getName();
        if (StringUtils.isEmpty(filename)) {
            return null;
        }
        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
        filename = filename.substring(filename.lastIndexOf("\\") + 1);
        return filename;
    }

    private void readFile(String savePath, FileItem item, String filename) throws IOException {
        //获取item中的上传文件的输入流
        InputStream in = item.getInputStream();
        //创建一个文件输出流
        FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
        //创建一个缓冲区
        byte buffer[] = new byte[1024];
        //判断输入流中的数据是否已经读完的标识
        int len = 0;
        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while ((len = in.read(buffer)) > 0) {
            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
            out.write(buffer, 0, len);
        }
        //关闭输入流
        in.close();
        //关闭输出流
        out.close();
        //删除处理文件上传时生成的临时文件
        item.delete();
    }

    private static String getTopicImageLocalSavePath(String ymd, UpLoadType upLoadType) {
        String localFilePath = Server.getProperty(AppConstants.UPLOAD_LOCAL_URL) + "/" + upLoadType.getFileName() + "/" + ymd;
        File file = new File(localFilePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(localFilePath + "目录不存在，需要创建");
            //创建目录
            file.mkdirs();
        }
        return localFilePath;
    }

    private static String getTopicImageRemoteSavePath(String ymd, UpLoadType upLoadType) {
        return Server.getProperty(AppConstants.UPLOAD_REMOTE_URL) + "/" + upLoadType.getFileName() + "/" + ymd;
    }
}
