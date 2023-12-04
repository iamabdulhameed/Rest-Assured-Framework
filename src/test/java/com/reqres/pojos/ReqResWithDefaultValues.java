package com.reqres.pojos;

import com.utils.RandomDataGenerator;
import com.utils.RandomDataTypeNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // we use toBuilder true when we want to let default value applies to all except to whom we gave our own value during call. refer the code.

public class ReqResWithDefaultValues {
    private String name = RandomDataGenerator.getRandomData(RandomDataTypeNames.FULLNAME);
    private String job = RandomDataGenerator.getRandomData(RandomDataTypeNames.JOB_FIELD);
}
