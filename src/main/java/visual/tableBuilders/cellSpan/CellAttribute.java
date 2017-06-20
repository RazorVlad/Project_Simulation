package visual.tableBuilders.cellSpan;

import java.awt.*;

public interface CellAttribute {

  void addColumn();

  void addRow();

  void insertRow(int row);

  Dimension getSize();

  void setSize(Dimension size);
}
