package cholog.supp.common.validation;

import cholog.supp.db.member.Member;

public class Validation {

    public static boolean verifyMember(Member member, Long writerId) {
        return member.getId().equals(writerId);
    }

}
