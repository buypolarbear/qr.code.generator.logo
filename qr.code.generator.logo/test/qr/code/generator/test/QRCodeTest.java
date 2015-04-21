package qr.code.generator.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import qr.code.generator.QRCodeGenerator;

public class QRCodeTest {
	
	@Test
	public void tset() throws IOException{
		String fileInputPath = "D:\\logo.png";
		String fileOutputPath = "D:\\qr.logo.png";
		
		String data = "www.baidu.com";
		
		QRCodeGenerator qrCodeGenerator = new QRCodeGenerator(fileInputPath, fileOutputPath, data);
		qrCodeGenerator.setMargin(50);
		qrCodeGenerator.setCellSize(50);
		
		String string = qrCodeGenerator.create();
		Assert.assertSame(string, "Image success !", string);
	}
}