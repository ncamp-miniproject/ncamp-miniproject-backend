package com.model2.mvc.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

public class TestBeanUtil {

    @Builder
    @Getter
    @Setter
    @ToString
    static class FromObject1 {
        private int intValue;
        private String strValue;
    }

    @Builder
    @Getter
    @Setter
    @ToString
    static class FromObject2 {
        private Long longValue;
        private FromObject1 fromObject1;
        private Object whichIsNotGonnaBeMapped;
    }

    @Builder
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class TargetObject {
        private int intValue;
        private String strValue;
        private float floatValue;
        private long longValue;
    }

    @Test
    public void doMapping() {
        FromObject1 fromObject1 = FromObject1.builder()
                .intValue(13)
                .strValue("sample")
                .build();

        TargetObject targetObject = null;
        try {
            targetObject = BeanUtil.doMapping(TargetObject.class, fromObject1);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(targetObject);
        assertThat(targetObject.intValue).isEqualTo(fromObject1.getIntValue());
        assertThat(targetObject.strValue).isEqualTo(fromObject1.getStrValue());
        assertThat(targetObject.floatValue).isZero();
    }

    @Test
    public void doMapping_fromMultipleBeans() {
        FromObject1 fromObject1 = FromObject1.builder()
                .intValue(13)
                .strValue("sample")
                .build();

        FromObject2 fromObject2 = FromObject2.builder()
                .longValue(13L)
                .fromObject1(fromObject1)
                .build();

        try {
            TargetObject targetObject = BeanUtil.doMapping(TargetObject.class, fromObject2, fromObject2.getFromObject1());

            assertThat(targetObject.getIntValue()).isEqualTo(fromObject1.getIntValue());
            assertThat(targetObject.getStrValue()).isEqualTo(fromObject1.getStrValue());
            assertThat(targetObject.getLongValue()).isEqualTo(fromObject2.getLongValue());
            assertThat(targetObject.getFloatValue()).isZero();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}