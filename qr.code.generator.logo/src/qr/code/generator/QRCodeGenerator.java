package qr.code.generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import qr.code.generator.util.ErrorCorrectLevel;
import qr.code.generator.util.QRCode;

public class QRCodeGenerator {
	private String fileInputPath;
	private String fileOutputPath;
	private String output = "png";
	private String errorCorrectLevel = "H";
	private Integer typeNumber = 10;
	
	private String data;
	
	private Integer margin = 3;
	private Integer cellSize = 10;
	
	@SuppressWarnings("unused")
	private QRCodeGenerator() {
		super();
	}

	/**
	 * @param fileInputPath LOGO 图片路径
	 * @param fileOutputPath 二维码图片输出路径
	 * @param data 生成二维码的源数据
	 */
	public QRCodeGenerator(String fileInputPath, String fileOutputPath, String data) {
		super();
		this.fileInputPath = fileInputPath;
		this.fileOutputPath = fileOutputPath;
		this.data = data;
	}

	/**
	 * @return
	 * if success return "Image success !";</br>
	 * if failure return "Image failure !";
	 * <pre>
	 * if(this.getData() == null){
	 * 	return "data is null !";
	 * }else if(this.getData().getBytes().length >= 120){
	 * 	return "data is too long !";
	 * }
	 * if(this.getFileOutputPath() == null){
	 * 	return "fileOutputPath is null !";
	 * }
	 * if(this.getCellSize().intValue() < 0){
	 * 	return "cellSize can not < 0 !";
	 * }
	 * if(this.getMargin().intValue() < 0){
	 * 	return "margin can not < 0 !";
	 * }
	 * </pre>
	 * @throws IOException
	 */
	public String create() throws IOException{
		if(this.getData() == null){
			return "data is null !";
		}else if(this.getData().getBytes().length >= 120){
			return "data is too long !";
		}
		if(this.getFileOutputPath() == null){
			return "fileOutputPath is null !";
		}
		if(this.getCellSize().intValue() < 0){
			return "cellSize can not < 0 !";
		}
		if(this.getMargin().intValue() < 0){
			return "margin can not < 0 !";
		}
		
		QRCode qrcode = getQRCode(data,	typeNumber, parseErrorCorrectLevel(errorCorrectLevel));
		
		BufferedImage image = qrcode.createImage(cellSize, margin, fileInputPath);
		File fileOutput = new File(fileOutputPath);
		if(ImageIO.write(image, output, fileOutput)){
			return "Image success !";
		}
		return "Image failure !";
	}
	
	private static int parseErrorCorrectLevel(String ecl) {
		if ("L".equals(ecl) ) {
    		return ErrorCorrectLevel.L;
    	} else if ("Q".equals(ecl) ) {
    		return ErrorCorrectLevel.Q;
    	} else if ("M".equals(ecl) ) {
	    	return ErrorCorrectLevel.M;
    	} else if ("H".equals(ecl) ) {
    		return ErrorCorrectLevel.H;
    	} else {
    		throw new IllegalArgumentException("invalid error correct level : " + ecl);
    	}
    }
	
	private static QRCode getQRCode(String data, int typeNumber, int errorCorrectLevel) {
		if (typeNumber == 0) {
			return QRCode.getMinimumQRCode(data, errorCorrectLevel);
		} else {
			QRCode qr = new QRCode();
			qr.setTypeNumber(typeNumber);
			qr.setErrorCorrectLevel(errorCorrectLevel);
			qr.addData(data);
			qr.make();
			return qr;
		}
	}

	public String getFileInputPath() {
		return fileInputPath;
	}

	/**
	 * @param fileInputPath
	 * LOGO 图片路径
	 */
	public void setFileInputPath(String fileInputPath) {
		this.fileInputPath = fileInputPath;
	}

	public String getFileOutputPath() {
		return fileOutputPath;
	}

	/**
	 * @param fileOutputPath
	 * 二维码图片输出路径
	 */
	public void setFileOutputPath(String fileOutputPath) {
		this.fileOutputPath = fileOutputPath;
	}

	public String getData() {
		return data;
	}

	/**
	 * @param data
	 * 生成二维码的源数据，长度小于120个英文字符
	 */
	public void setData(String data) {
		this.data = data;
	}

	public String getOutput() {
		return output;
	}

	/**
	 * @param output
	 * 输出图片的格式PNG
	 */
	public void setOutput(String output) {
		this.output = output = "png";
	}

	public String getErrorCorrectLevel() {
		return errorCorrectLevel;
	}

	/**
	 * @param errorCorrectLevel
	 * 指定容错级别：L，M，Q，H，容错率分别是：7%，15%，25%，30%，默认H
	 */
	public void setErrorCorrectLevel(String errorCorrectLevel) {
		this.errorCorrectLevel = errorCorrectLevel = "H";
	}

	public Integer getTypeNumber() {
		return typeNumber;
	}

	/**
	 * @param typeNumber
	 * 二维码密集程度，直接影响存储量，默认是10
	 */
	public void setTypeNumber(Integer typeNumber) {
		this.typeNumber = typeNumber = 10;
	}

	public Integer getMargin() {
		return margin;
	}

	/**
	 * @param margin
	 * 二维码图片边距
	 */
	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public Integer getCellSize() {
		return cellSize;
	}

	/**
	 * @param cellSize
	 * 每个色块的大小，用于改变输出图片的大小
	 */
	public void setCellSize(Integer cellSize) {
		this.cellSize = cellSize;
	}
}