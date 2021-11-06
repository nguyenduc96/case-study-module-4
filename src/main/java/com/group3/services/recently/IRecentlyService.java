package com.group3.services.recently;

import com.group3.models.recently.Recently;
import com.group3.services.IGeneralService;

import java.util.List;

public interface IRecentlyService extends IGeneralService<Recently> {
    List<Long> allMusicsViews();
}
