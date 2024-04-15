package hello.itemservice.domain.item;

public enum ItemType {
    BOOK("도시"),FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }
}
