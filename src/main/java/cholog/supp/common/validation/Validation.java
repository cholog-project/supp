package cholog.supp.common.validation;

import cholog.supp.db.member.Member;
import java.util.List;

public class Validation {

    public static boolean verifyMember(Member member, Long compareMemberId) {
        return member.getId().equals(compareMemberId);
    }

    public static void verifyEmptyList(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("리스트가 비어있습니다.");
        }
    }
}
