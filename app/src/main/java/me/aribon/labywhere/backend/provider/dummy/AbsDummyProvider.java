package me.aribon.labywhere.backend.provider.dummy;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import me.aribon.labywhere.backend.provider.AbsStorage;

/**
 * Created by anthony.ribon
 * On 26/06/2017
 */

public abstract class AbsDummyProvider<D> extends AbsStorage<D> {

  abstract String getFileName();

  protected String loadDummyFromAsset(Context context) {
    String json = null;
    try {
      InputStream is = context.getAssets().open(getFileName());
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
    return json;
  }
}
