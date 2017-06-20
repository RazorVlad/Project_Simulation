package visual.frames.modelResult;

import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import visual.Format;
import visual.topMenu.ProjectFileFilter;

public class XLS {

	private static Logger LOG = Logger.getLogger(XLS.class);

	static void createXLSX(Workbook workbook, Sheet sheet, JTable table, String chooseSphere,
			String manufacturingType, String chooseVED, int[] mainHeaders, int[] headers) {

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setItalic(true);
		style.setFont(font);

		CellStyle style1 = workbook.createCellStyle();
		Font font1 = workbook.createFont();
		font1.setUnderline((byte) 1);
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style1.setFont(font1);

		CellStyle style2 = workbook.createCellStyle();
		Font font2 = workbook.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style2.setFont(font2);

		sheet.setColumnWidth(0, 65 * 256);

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(chooseSphere + "/" + chooseVED + "/" + manufacturingType + " ������������");
		cell.setCellStyle(style2);
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, table.getColumnCount() - 2);
		// createregionStyle(workbook, sheet, region, CellStyle.BORDER_MEDIUM);
		sheet.addMergedRegion(region);
		row = sheet.createRow(1);
		int k = 0;
		for (int j = 0; j < table.getColumnCount(); j++) {
			if (j != 1) {
				cell = row.createCell(k);
				try {
					cell.setCellValue((table.getColumnName(j)));
				} catch (Exception e) {
					cell.setCellValue("");
					LOG.error("emptyValue", e);
				}
				k++;
				XLS.createCellStyle(workbook, cell, true, false, false, CellStyle.BORDER_THIN);
			}
		}

		for (int i = 0; i < table.getRowCount(); i++) {
			row = sheet.createRow(i + 2);
			k = 0;
			for (int j = 0; j < table.getColumnCount(); j++) {
				if (j != 1) {
					cell = row.createCell(k);
					try {
						if (j == 0) {
							cell.setCellValue(XLS.stripTags(table.getValueAt(i, k).toString()));
							for (int header : headers) {
								if ((i) == header) {
									cell.setCellStyle(style);
								}
							}
							for (int mainHeader : mainHeaders) {
								if ((i) == mainHeader) {
									cell.setCellStyle(style1);
								}
							}
						} else {
							cell.setCellValue(Format.getDouble(table.getValueAt(i, j).toString()));
						}
					} catch (Exception e) {
						cell.setCellValue("");
						LOG.error(e.getMessage(), e);
					}
					k++;
				}
			}
		}

		XLS.createregionStyle(workbook, sheet,
				new CellRangeAddress(1, table.getRowCount() + 1, 0, table.getColumnCount() - 2),
				CellStyle.BORDER_THIN);
		XLS.createregionStyle(workbook, sheet, new CellRangeAddress(1, table.getRowCount() + 1, 0, 0),
				CellStyle.BORDER_THIN);

	}

	public static void createCellStyle(Workbook wb, Cell cell, boolean boldweight, boolean underline,
			boolean italic, short styleBorder) {

		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(styleBorder);
		style.setBorderLeft(styleBorder);
		style.setBorderRight(styleBorder);
		style.setBorderTop(styleBorder);
		Font font = wb.createFont();
		if (boldweight) {
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		if (underline) {
			font.setUnderline((byte) 1);
		}
		if (italic) {
			font.setItalic(true);
		}
		style.setFont(font);
		cell.setCellStyle(style);
	}

	public static void createCellStyle(Workbook wb, Cell cell) {

		CellStyle style = wb.createCellStyle();
		HSSFFont hSSFFont = (HSSFFont) wb.createFont();
		// hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		// hSSFFont.setFontHeightInPoints((short) 16);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// hSSFFont.setColor(HSSFColor.BLACK.index);
		style.setFont(hSSFFont);
		cell.setCellStyle(style);
	}

	public static void createregionStyle(Workbook wb, Sheet sheet, CellRangeAddress region, short styleBorder) {
		RegionUtil.setBorderTop(styleBorder, region, sheet, wb);
		RegionUtil.setBorderLeft(styleBorder, region, sheet, wb);
		RegionUtil.setBorderRight(styleBorder, region, sheet, wb);
		RegionUtil.setBorderBottom(styleBorder, region, sheet, wb);
	}

	public static String stripTags(String xmlStr) {
		xmlStr = xmlStr.replaceAll("<(.)+?>", "");
		xmlStr = xmlStr.replaceAll("<(br)+?>", "");
		xmlStr = xmlStr.replaceAll("<(html)+?>", "");
		xmlStr = xmlStr.replaceAll("<(\n)+?>", "");

		return xmlStr;
	}

	public static double[][] readXls() {
		double[][] data = null;

		JFileChooser fileopen = new JFileChooser();
		fileopen.setFileFilter(new ProjectFileFilter("Excel 97-2003", ".xls"));
		fileopen.setCurrentDirectory(new java.io.File("."));
		int ret = fileopen.showDialog(null, "������� ����");
		if (ret == JFileChooser.APPROVE_OPTION) {
			String path = fileopen.getSelectedFile().getAbsolutePath();

			try {
				POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);
				HSSFRow row;
				HSSFCell cell;

				int rows;
				rows = sheet.getPhysicalNumberOfRows();

				int cols = 0;
				int tmp;

				// This trick ensures that we get the data properly even if it
				// doesn't start from first few rows
				for (int i = 0; i < 10 || i < rows; i++) {
					row = sheet.getRow(i);
					if (row != null) {
						tmp = sheet.getRow(i).getPhysicalNumberOfCells();
						if (tmp > cols) {
							cols = tmp;
						}
					}
				}
				data = new double[rows][cols];
				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r);
					if (row != null) {
						for (int c = 0; c < cols; c++) {

							cell = row.getCell((short) c);
							if (cell != null) {
								data[r][c] = cell.getNumericCellValue();
							} else {
								data[r][c] = 0;
							}
						}
					}
				}
			} catch (Exception ioe) {
				LOG.error(ioe.getMessage(), ioe);
			}
		}
		return data;
	}

}
