package com.eshequ.gatherservice.service.liquidate;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface LiquidateService<T> {

    List<T> pullLiquidateFile(String liquidate_date) throws IOException;

}
