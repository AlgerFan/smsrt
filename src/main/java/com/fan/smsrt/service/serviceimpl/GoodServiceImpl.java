package com.fan.smsrt.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fan.smsrt.dao.GoodDao;
import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.service.GoodService;
import com.fan.smsrt.util.CheckImage;
import com.fan.smsrt.util.UploadUtil;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author 阿杰
 * @date 2018/7/21 17:45
 * @Description
 */
@Service
@Log4j2
public class GoodServiceImpl implements GoodService {

    @Resource
    private GoodDao goodDao;

    @Override
    public Result selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodDao.selectAllGoods();
        PageInfo pageInfo = new PageInfo<>(goods);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }

    @Override
    public Result insert(int storeId, Goods goods, MultipartFile[] myFileNames, HttpServletRequest request, HttpSession session) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if (goods == null || goods.getName() == null || goods.getName().equals("") || goods.getMoney() == 0 ||
                goods.getInventory() == 0 || goods.getType() == null || goods.getType().equals("") || goods.getSpecification() == null || goods.getSpecification().equals("")) {
            result.setMessage("填写信息不完整，修改失败 ");
            return result;
        }
        log.info("前台商品信息："+goods);
        if (myFileNames.length == 0) {
            result.setMessage("图片不能为空");
            return result;
        }
        Goods goodsDb = goodDao.selectByName(goods.getName());
        if (goodsDb != null) {
            result.setMessage("添加失败，该商品名已存在 ");
            return result;
        } else {
            String goodsImg = "";
            String detail = "<p>";
            int num = 0;
            for (MultipartFile myFileName : myFileNames) {
                String fileName = myFileName.getOriginalFilename();
                assert fileName != null;
                if (CheckImage.verifyImage(fileName)) {
                    String realName;
                    String fileNameExtension = fileName.substring(fileName.indexOf("."));
                    // 生成实际存储的真实文件名
                    realName = UUID.randomUUID().toString() + fileNameExtension;
                    // "/upload/goodsImg"是你自己定义的上传目录
                    String path = "/upload/goodsImg/" + realName;
                    UploadUtil uploadUtil = new UploadUtil();
                    if (!uploadUtil.saveFile(myFileName, path)) {
                        result.setMessage("图片上传失败 ");
                        return result;
                    }
                    if (num == 0) {
                        goodsImg = "/api/path/upload/goodsImg/" + realName;
                    } else {
                        detail = detail + "<img src=\"/api/path/upload/goodsImg/" + realName + "\" style=\"max-width: 100%;\">";
                    }
                    num++;
                } else {
                    result = new Result(ResultCodeEnum.FAIL);
                    result.setMessage("图片格式不正确");
                    return result;
                }
            }
            goods.setImagePath(goodsImg);
            goods.setDetails(detail + "<br></p>");
            goods.setStoreId(storeId);
            goodDao.insert(goods);
            log.info("添加商品成功，商品数据为："+goods);
            result = new Result(ResultCodeEnum.SUCCESS, goods);
            result.setMessage("添加成功");
            return result;
        }
    }

    /*
    <p><img src="/api/path/upload/goodsImg/fuwenben30478f30-ece3-4677-b12c-e5d145522fc3.jpg" style="max-width: 100%;">
    <img src="/api/path/upload/goodsImg/fuwenben56c5ccec-db69-4c6c-afd0-6885354b71e6.jpg" style="max-width: 100%;">
    <img src="/api/path/upload/goodsImg/fuwenbenbeb1d57c-257e-4d42-b9f2-5550a9a968b9.jpg" style="max-width: 100%;">
    <br></p>
     */

    @Override
    public Result delete(int goodsId, int storeId) {
        Result result;
        Goods goods = goodDao.selectStoreById(goodsId, storeId);
        if (goodsId == 0 || goods == null) {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该商品不存在");
            return result;
        }
        if (new UploadUtil().deleteFile(goods.getImagePath().substring(9))) {
            goodDao.delete(goodsId);
            result = new Result(ResultCodeEnum.SUCCESS);
            result.setMessage("删除成功");
            return result;
        } else {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("删除失败");
            return result;
        }
    }

    @Override
    public Result selectGoodsById(int goodsId) {
        Result result;
        Goods goods = goodDao.selectById(goodsId);
        if (goods == null) {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该商品不存在");
            return result;
        }
        result = new Result(ResultCodeEnum.SUCCESS, goods);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result update(Goods goods, int goodsId, int storeId) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if (goodsId == 0 || goods == null || goods.getName() == null || goods.getMoney() == 0 ||
                goods.getInventory() == 0 || goods.getType() == null || goods.getSpecification() == null) {
            result.setMessage("填写信息不完整，修改失败");
            return result;
        }
        Goods goodsDb = goodDao.selectStoreById(goodsId, storeId);
        if (goodsDb != null) {
            result = new Result(ResultCodeEnum.SUCCESS, goods);
            goodDao.update(goods, goodsId);
            result.setMessage("修改成功");
            return result;
        }
        result = new Result(ResultCodeEnum.FAIL);
        return result;
    }

    @Override
    public Result searchGoods(String search, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodDao.searchGoods(search);
        PageInfo pageInfo = new PageInfo<>(goods);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }

    @Override
    public Result typeSearchGoods(String search, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodDao.typeSearchGoods(search);
        PageInfo pageInfo = new PageInfo<>(goods);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }

    @Override
    public Result selectStoreGoods(int pageNum, int pageSize, int storeId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodDao.selectStoreGoods(storeId);
        PageInfo pageInfo = new PageInfo<>(goods);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }

    @Override
    public PageInfo selectGoods(String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodDao.selectGoods(name);
        return new PageInfo<>(goods);
    }

    @Override
    public String[] uploadImg(MultipartFile myFileName, HttpSession session, HttpServletRequest request) {
        String realName = "";
        if (myFileName.getOriginalFilename() != null) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."));
            // 生成实际存储的真实文件名
            realName = "fuwenben" + UUID.randomUUID().toString() + fileNameExtension;
            // "/upload/goodsImg"是你自己定义的上传目录
            String goodsImg = "/upload/goodsImg/" + realName;
            UploadUtil uploadUtil = new UploadUtil();
            uploadUtil.saveFile(myFileName, goodsImg);
        }
        return new String[]{"/api/path/upload/goodsImg/" + realName};
    }

    @Override
    public Result adminInsert(Goods goods, MultipartFile myFileName, HttpServletRequest request, HttpSession session) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if (goods == null || goods.getName() == null || goods.getMoney() == 0 || goods.getDetails() == null ||
                goods.getInventory() == 0 || goods.getType() == null || goods.getSpecification() == null) {
            result.setMessage("填写信息不完整，修改失败");
            return result;
        }
        if (myFileName.getOriginalFilename() == null) {
            result.setMessage("图片不能为空");
            return result;
        }
        String fileName = myFileName.getOriginalFilename();
        if (CheckImage.verifyImage(fileName)) {
            String realName;
            String goodsImg;
            String fileNameExtension = fileName.substring(fileName.indexOf("."));
            // 生成实际存储的真实文件名
            realName = UUID.randomUUID().toString() + fileNameExtension;
            // "/upload/goodsImg"是你自己定义的上传目录
            String path = "/upload/goodsImg/" + realName;
            UploadUtil uploadUtil = new UploadUtil();
            boolean isUpload = uploadUtil.saveFile(myFileName, path);
            if (!isUpload) {
                result.setMessage("图片上传失败");
                return result;
            }
            goodsImg = "/api/path/upload/goodsImg/" + realName;
            Goods goodsDb = goodDao.selectByName(goods.getName());
            goods.setImagePath(goodsImg);
            goods.setStoreId(1);
            if (goodsDb != null) {
                result.setMessage("添加失败，该商品名已存在");
                return result;
            } else {
                goodDao.insert(goods);
                result = new Result(ResultCodeEnum.SUCCESS);
                result.setMessage("添加成功");
                return result;
            }
        } else {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("图片格式不正确");
            return result;
        }
    }

    @Override
    public Result adminDelete(int goodsId) {
        Result result;
        Goods goods = goodDao.selectById(goodsId);
        if (goodsId == 0 || goods == null) {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该商品不存在");
            return result;
        }
        if (new UploadUtil().deleteFile(goods.getImagePath().substring(9))) {
            goodDao.delete(goodsId);
            result = new Result(ResultCodeEnum.SUCCESS);
            result.setMessage("删除成功!");
            return result;
        } else {
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("删除失败");
            return result;
        }
    }

    @Override
    public Result adminUpdate(Goods goods, int goodsId) {
        Result result = new Result(ResultCodeEnum.FAIL);
        if (goodsId == 0 || goods == null || goods.getName() == null || goods.getMoney() == 0 ||
                goods.getInventory() == 0 || goods.getType() == null || goods.getSpecification() == null) {
            result.setMessage("填写信息不完整，修改失败 ");
            return result;
        }
        Goods goodsDb = goodDao.selectById(goodsId);
        if (goodsDb != null) {
            result = new Result(ResultCodeEnum.SUCCESS);
            goodDao.update(goods, goodsId);
            result.setMessage("修改成功 ");
            return result;
        }
        result = new Result(ResultCodeEnum.FAIL);
        return result;
    }

}
