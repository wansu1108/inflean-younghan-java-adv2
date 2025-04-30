package annotation.meta;

@AnnoMeta
public class MetaData {
    // @AnnoMeta 컴파일 오류
    private String meta;

    @AnnoMeta
    public String getMeta() {
        return meta;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        AnnoMeta typeAnno = MetaData.class.getAnnotation(AnnoMeta.class);
        System.out.println("typeAnno = " + typeAnno);


        AnnoMeta methodAnno = MetaData.class.getMethod("getMeta").getAnnotation(AnnoMeta.class);
        System.out.println("methodAnno = " + methodAnno);
    }
}
