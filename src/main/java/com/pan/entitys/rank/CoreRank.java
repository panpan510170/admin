package com.pan.entitys.rank;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 榜单控制表,主要控制榜单的使用,清除,维度,权重,时间
 * @author pan
 * @date 2019/6/20 16:37
 *
 * @Entity
 * 标识这个实体类是一个JPA实体，告诉JPA在程序运行的时候记得生成这个实体类所对应的表~！
 *
 * @Table（name = "自定义的表名"）
 * 自定义设置这个实体类在数据库所对应的表名！
 *
 * @Id
 * 把这个类里面所在的变量设置为主键Id。
 *
 * @GeneratedValue
 * 设置主键的生成策略，这种方式依赖于具体的数据库，如果数据库不支持自增主键，那么这个类型是没法用的。
 *
 * @Basic
 * 表示一个简单的属性到数据库表的字段的映射, 对于没有任何标注的getXxxx()方法, 默认 即为 @Basic fetch: 表示该属性的读取策略,有EAGER和LAZY两种,分别表示主支抓取和延迟加载,默认为EAGER.
 *
 * @Column（name = "自定义字段名"，length = "自定义长度"，nullable = "是否可以空"，unique = "是否唯一"，columnDefinition = "自定义该字段的类型和长度"）
 *
 * @Transient
 * 表示该属性并非一个到数据库表的字段的映射, ORM框架将忽略该属性. 如果一个属性并非数据库表的字段映射,就务必将其标示为 @Transient ,否则,ORM框架默认其注 解为 @Basic对于昨天实践的注解已经解析完了啦！那么今天为了接着对JPA表之间的各种关系的映射继续实践，我们再新建几个实体类！接下来我们分别新建了
 * Author类，Address类和Sex枚举类来举例完成实践！
 * 来我们先看看Author类
 *
 * @Temporal
 * 当我们使用到java.util包中的时间日期类型，则需要@Temporal注释来说明转化成java.util包中的类型。其中包含三种转化类型分别是：
 * java.sql.Date日期型，精确到年月日，例如“2008-08-08”
 * java.sql.Time时间型，精确到时分秒，例如“20:00:00”
 * java.sql.Timestamp时间戳，精确到纳秒，例如“2008-08-08 20:00:00.000000001”
 *
 * @Enumerated（"需要定义存入数据库的类型”)
 * 使用@Enumerated映射枚举字段，我这里为性别建立了性别的枚举类型，而后面跟上的是存入数据库以String类型存入。
 *
 * @Embedded和@Embeddable
 * 当一个实体类要在多个不同的实体类中进行使用，而本身又不需要独立生成一个数据库表，这就是需要使用@Embedded、@Embeddable的时候了。
 * 我们在Address里加上了@Embeddable这个注解表示，Address这个类是一个可以被嵌套的类，而在Author类中，
 * 我们声明了一个Address类型的变量address，然后给它加上@Embedded注解，意思是我们要在Author类嵌套Address类，
 * 当然这里涉及到一个生命周期的问题。我们先看看生成的表再解析一下这个问题！
 *
 * 当被引用的对象和主对象拥有相同的生命周期的时候才考虑使用@Embedded和@Embeddable。
 * 简单的说就是Author类存在的时候才会有Address类，当Author类不存在的时候，
 * 对应Author类所以诞生的Address类也应该是不存在的。通俗的说就是作者存在的时候才会有这个作者的地址。
 * 而不会是有一个地址存在着却没有人属于这个地址。而且内嵌类会和主类生成一张表，所以内嵌类对应主类
 * 应该是要唯一的和拥有相同生命周期的。
 * 关于@Embedded和@Embeddable这里附上一个解析得更加详尽的地址：
 * http://blog.csdn.net/lmy86263/article/details/52108130
 * @ElementCollection
 * 集合映射，当实体类包含多个相同类型的变量的时候就可以使用@ElementCollection来声明这个变量，
 * 而JPA会为此生成两个关联的表。例如一个人有家庭住址，也有单位地址；但是“地址”并不能失去人的存在而存在，
 * 所以是一个失去独立性的实体类；所以地址不能映射为一个实体，这时就需要映射为组件，及人的信息里边包含地址。
 * 是整体与部分的关系。但由于这个地址可能有多个。比如公司地址、出生地址、家庭地址等。
 */
@Entity
@Table(name = "t_core_rank")
@org.hibernate.annotations.Table(appliesTo = "t_core_rank",comment="榜单控制表")
@Data
public class CoreRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,unique = true,columnDefinition = "varchar(100) comment '榜单名称'")
    private String name;
    @Column(name = "dimension_type",nullable = false,columnDefinition = "int(2) comment '维度 1有2没有'")
    private int dimensionType;
    @Column(name = "basics",nullable = false,columnDefinition = "bigint(20) comment '权重,根据场景自行分配'")
    private Long basics;
    @Column(name = "clear_time",columnDefinition = "bigint(20) default 0 comment '清除榜单数据时间,可以为空'")
    private Long clearTime;
    @Column(name = "start_time",nullable = false,columnDefinition = "bigint(20) comment '开始使用时间'")
    private Long startTime;
    @Column(name = "end_time",nullable = false,columnDefinition = "bigint(20) comment '结束使用时间,若无期限可以使用253402271999000(9999-12-31 23:59:59)'")
    private Long endTime;
    @Column(name = "create_time",nullable = false,columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
    @Column(name = "pro_name",nullable = false,columnDefinition = "varchar(100) comment '项目名称'")
    private String proName;
}
