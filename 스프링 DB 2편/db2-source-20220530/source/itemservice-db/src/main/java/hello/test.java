package hello;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class test {
    @Data
    @AllArgsConstructor
    static class Member{
        private Long id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;
            return Objects.equals(id, member.id) && Objects.equals(name, member.name);
        }

        @Override
        public int hashCode() {
            return 12;
        }
    }
    public static void main(String[] args) {

        Map<Member, String> map = new HashMap<>();
        Member member = new Member(1L, "memberA");
        Member member2 = new Member(2L, "memberB");

        map.put(member, "회원1");
        map.put(member2, "회원2");
    }
}
