/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package com.taobao.text.renderers;

import com.taobao.text.LineRenderer;
import com.taobao.text.Renderer;
import com.taobao.text.ui.LabelElement;
import com.taobao.text.ui.Overflow;
import com.taobao.text.ui.RowElement;
import com.taobao.text.ui.TableElement;
import com.taobao.text.util.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FileRenderer extends Renderer<File> {

  @Override
  public Class<File> getType() {
    return File.class;
  }

  @Override
  public LineRenderer renderer(Iterator<File> stream) {

    //
    List<File> files = Utils.list(stream);
    Collections.sort(files);
    TableElement table = new TableElement().overflow(Overflow.WRAP).rightCellPadding(1);
    SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm");
    Date date = new Date();

    //
    for (File file : files) {
      String mode = (file.isDirectory() ? "d" : "-")
          + (file.canRead() ? "r" : "-")
          + (file.canWrite() ? "w" : "-")
          + (file.canExecute() ? "x" : "-")
          ;
      String length = Long.toString(file.length());
      date.setTime(file.lastModified());
      String lastModified = format.format(date);
      table.row(
        mode,
        length,
        lastModified,
        file.getName()
      );
    }

    //
    return table.renderer();
  }
}
