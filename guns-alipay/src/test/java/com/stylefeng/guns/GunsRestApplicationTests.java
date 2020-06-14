package com.stylefeng.guns;

import com.stylefeng.guns.rest.AlipayApplication;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlipayApplication.class)
public class GunsRestApplicationTests {

	@Autowired
	private FTPUtil ftpUtil;

	@Test
	public void contextLoads() {
		File file=new File("F:\\tmp\\qrCode\\qr-aac983a1d3c743acb906b59a31312c3f.png");
		boolean b=ftpUtil.uploadFle("qr-aac983a1d3c743acb906b59a31312c3f.png",file);
		System.out.println("上传是否成功:"+b);
	}

}
