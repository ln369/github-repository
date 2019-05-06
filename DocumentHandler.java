package com.cn.ln.Handler;

import com.cn.ln.Beans.Document;
import com.cn.ln.Beans.PageModel;
import com.cn.ln.Beans.User;
import com.cn.ln.Service.IDocumentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentHandler {
    @Autowired
    private IDocumentService documentService;
    @RequestMapping("/findDocument.do")
    private String findDocument(@RequestParam(defaultValue = "1") int pageIndex, PageModel pageModel, Document document, Model model){
        pageModel.setPageIndex(pageIndex);
        int counts=documentService.findDocumentCount(document);
        pageModel.setRecordCount(counts);
        List<Document> documents=documentService.findDocument(pageModel,document);
        model.addAttribute("documents",documents);
        model.addAttribute("document",document);
        model.addAttribute("pageModel",pageModel);
        for (Document d:documents){
            System.out.println(d);
        }
//        return null;
        return "/jsp/document/document.jsp";
    }
    @RequestMapping("/addDocument.do")
    private String addDocument(Document document, Model model, MultipartFile file, HttpSession session) {
        User login_user= (User) session.getAttribute("login_user");
        document.setUser(login_user);
        String fileName=System.currentTimeMillis()+"-"+file.getOriginalFilename();
        document.setFilename(fileName);
        String path="D:/upload";
        File files=new File(path,fileName);
        if(!files.exists()){
            files.mkdirs();
        }
        try {
            file.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rows=documentService.addDocument(document);

        if (rows>0){
            return "redirect:/document/findDocument.do";
        }else{
            model.addAttribute("fail", "信息添加失败！");
            return "/jsp/fail.jsp";
        }
    }
    @RequestMapping("/updateDocument.do")
    public String updateDocument(Document document,String flag,Model model,HttpSession session) throws IOException {
        if (flag == null) {
            document = documentService.findDocumentById(document.getId());
            model.addAttribute("document", document);
            return "/jsp/document/showUpdateDocument.jsp";
        } else {
            System.out.println(document);
            //修改时，如果原来有文件，则把原文件删除
            if (!document.getFile().isEmpty()) {
                String path = "D:/upload";//session.getServletContext().getRealPath("/upload");
                //在前台页面新传进来修改好的内容，在这里会被覆盖成原来的内容，所以这里需要重新定义一个Document类，
                Document document2 = documentService.findDocumentById(document.getId());
                File documentFile = new File(path, document2.getFilename());
                if (documentFile.exists()) {
                    documentFile.delete();
                }
                //然后把新修改的文件给保存到本地
                String fileName = System.currentTimeMillis() + "-" + document.getFile().getOriginalFilename();
                document.setFilename(fileName);
                //document.getFile().transferTo(documentFile);
                document.getFile().transferTo(new File(path,fileName));
            }
            User login_user = (User) session.getAttribute("login_user");
            document.setUser(login_user);
            int rows = documentService.updateDocument(document);
            if (rows > 0) {
                return "redirect:/document/findDocument.do";
            } else {
                model.addAttribute("fail", "信息添加失败！");
                return "/jsp/fail.jsp";
            }
        }
    }
    @RequestMapping("/removeDocument.do")
    public String removeDocument(Integer ids[],Model model){

        String path = "D:/upload";
        int rows = 0;
        for (Integer id:ids){
            Document target = documentService.findDocumentById(id);
            File targetFile = new File(path,target.getFilename());//不太明白
            if (targetFile.exists()){
                targetFile.delete();
            }
            int i = documentService.removeDocument(id);
            if (i > 0){
                rows++;
            }
        }
        if (rows == ids.length){
            return "/document/findDocument.do";
        }else {
            model.addAttribute("fail","文件删除失败！");
            return "/jsp/fail.jsp";
        }
        }
    @RequestMapping("/downLoad.do")
    public ResponseEntity<byte[]> download(Integer id, HttpServletRequest request) throws IOException {
        Document target = documentService.findDocumentById(id);
        String path = "D:/upload";
        String filename = target.getFilename();
        File file = new File(path,target.getFilename());

        HttpHeaders headers = new HttpHeaders();
        filename = processFileName(request,filename);//new String(filename.getBytes("UTF-8"),"iso8859-1");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",filename);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }

    //IE、chrom、Firefox文件中文乱码问题
    public String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }
}

