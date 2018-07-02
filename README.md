# MVVMProject
MVVM模式，dataBinding框架
## 前言
MVC是Model-View-Controller的缩写，它将应用程序划分为三个部分：

Model: 模型（用于封装与应用程序的业务逻辑相关的数据以及对数据的处理方法）

View: 视图（渲染页面）

Controller: 控制器（M和V之间的连接器，用于控制应用程序的流程，及页面的业务逻辑）

MVC优点：实现关注点分离，即应用程序中的数据模型与业务和展示逻辑解耦。在客户端开发中，就是将模型(M-数据、操作数据)、视图(V-显示数据的HTML元素)之间实现代码分离，松散耦合，使之成为一个更容易开发、维护和测试的客户端应用程序。

MVC缺点：

不适合小型，中等规模的应用程序，花费大量时间将MVC应用到规模并不是很大的应用程序通常会得不偿失。

视图与控制器间过于紧密连接，视图与控制器是相互分离，但却是联系紧密的部件，视图没有控制器的存在，其应用是很有限的，反之亦然，这样就妨碍了他们的独立重用。

视图对模型数据的低效率访问，依据模型操作接口的不同，视图可能需要多次调用才能获得足够的显示数据。对未变化数据的不必要的频繁访问，也将损害操作性能

MVP（Model-View-Presenter）是MVC的改良模式，由IBM的子公司Taligent提出。和MVC的相同之处在于：Controller/Presenter负责业务逻辑，Model管理数据，View负责显示只不过是将 Controller 改名为 Presenter，同时改变了通信方向。

MVP特点：

M、V、P之间双向通信。

View 与 Model 不通信，都通过 Presenter 传递。Presenter完全把Model和View进行了分离，主要的程序逻辑在Presenter里实现。

View 非常薄，不部署任何业务逻辑，称为”被动视图”（Passive View），即没有任何主动性，而 Presenter非常厚，所有逻辑都部署在那里。

Presenter与具体的View是没有直接关联的，而是通过定义好的接口进行交互，从而使得在变更View时候可以保持Presenter的不变，这样就可以重用。不仅如此，还可以编写测试用的View，模拟用户的各种操作，从而实现对Presenter的测试–从而不需要使用自动化的测试工具。

MVP优点：

模型与视图完全分离，我们可以修改视图而不影响模型；

可以更高效地使用模型，因为所有的交互都发生在一个地方——Presenter内部；

我们可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑。这个特性非常的有用，因为视图的变化总是比模型的变化频繁；

如果我们把逻辑放在Presenter中，那么我们就可以脱离用户接口来测试这些逻辑（单元测试）。

MVP缺点：

视图和Presenter的交互会过于频繁，使得他们的联系过于紧密。也就是说，一旦视图变更了，presenter也要变更。

MVVM是Model-View-ViewModel的简写。主要目的是分离视图（View）和模型（Model）

MVVM优点：

低耦合，视图（View）可以独立于Model变化和修改，一个ViewModel可以绑定到不同的”View”上，当View变化的时候Model可以不变，当Model变化的时候View也可以不变。

可重用性，可以把一些视图逻辑放在一个ViewModel里面，让很多view重用这段视图逻辑。

独立开发，开发人员可以专注于业务逻辑和数据的开发（ViewModel），设计人员可以专注于页面设计，使用Expression Blend可以很容易设计界面并生成xml代码。

可测试，界面向来是比较难于测试的，而现在测试可以针对ViewModel来写。

DataBinding是Google在2015年7月发布的Android Studio v1.3.0 版本上引入的，在2016年4月Android Studio v2.0.0 上正式支持。引入之初，不支持双向绑定，目前已经支持了。

DataBinding 是基于MVVM思想实现数据和UI绑定的的框架，有了 Data Binding，在Android中也可以很方便的实现MVVM。

在引入DataBinding之前，我们需要敲很多很鸡肋的代码，如 findViewById()、setText()，setVisibility()，setEnabled() 或 setOnClickListener() 等，通过 Data Binding , 我们可以通过声明式布局以精简的代码来绑定应用程序逻辑和布局，这样就不用编写大量的毫无营养的代码了。

## DataBinding使用
### 1、在使用的module的build.gradle中加入
android {

       dataBinding {

        enabled true

    }

}




### 2、数据类
可用ObservableField，也可以用LiveData，官方推荐LiveData，LiveData会遵从其他应用组件（如activity，fragment）的生命周期，它只会在UI组件处在active状态（如activity处在started和resumed ）时才会推送数据。这样避免了我们UI展示数据时，需要检查下组件是否存在。具体后续说明。这里demo用的ObservableField.。

public class UserBean {

public ObservableFieldname =new ObservableField<>();

    public ObservableFieldage =new ObservableField<>();

    public ObservableFieldsex =new ObservableField<>();

}




### 3、布局文件
布局文件根标签是layout。layout中包含data标签，以及view。data部分是对应使用model。import说明是哪个具体的数据类，可定义alias 别名。variable定义变量，给view中引用，type为变量的类型。

### 4、在activity中使用
MainMvvmBinding 这个是根据布局文件自动生成的，布局文件命名为main_mvvm，则生成MainMvvmBinding，如果命名为activity_main，则生成ActivityMainBinding。
public class MainActivityextends AppCompatActivity {

private MainMvvmBinding binding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_mvvm);

        UserBean userBean =new UserBean();

        userBean.name.set("张三");

        userBean.age.set(28);

        userBean.sex.set("男");

        binding.setUser(userBean);

    }

}
### 总结

通过dataBinding 实现mvvm模式，使得数据和UI绑定，代码更加简洁，大大的减少了代码量。通过 Data Binding , 我们可以通过声明式布局以精简的代码来绑定应用程序逻辑和布局。期待在项目中的使用~~
