package com.spoof.xuanke.web.service;

import com.spoof.xuanke.pojo.Major;
import com.spoof.xuanke.pojo.ProfessionalInfo;

import java.util.List;

public interface MajorService {

    List<Major> getMajors();

    List<ProfessionalInfo> getProfessionalList(int pagenum, int pagesize);

    int getProfessionalCount();
}
