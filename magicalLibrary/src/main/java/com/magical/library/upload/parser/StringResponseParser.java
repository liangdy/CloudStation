package com.magical.library.upload.parser;

/**
 * Project: TShow
 * FileName: StringResponseParser.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class StringResponseParser extends BaseResponseParser {

    /**
     * 默认不处理
     *
     * @param responseStr
     * @return
     */
    @Override
    public ParserResult<String> process(final String responseStr) throws Exception {
        ParserResult<String> result = new ParserResult<String>(responseStr) {
            @Override
            public boolean isSuccessful() {
                return true;
            }

            @Override
            public String getMsg() {
                return null;
            }
        };
        return result;
    }
}
