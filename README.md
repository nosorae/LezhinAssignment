# Overview
- 기본적인 요구사항 모두 완료했습니다. (검색어 필드 요구사항도 아래 두 이슈에 포함됩니다.)
    - [검색화면 요구사항](https://github.com/nosorae/LezhinAssignment/issues/4) - 완료 🟢
    - [북마크화면 요구사항](https://github.com/nosorae/LezhinAssignment/issues/5) - 완료 🟢
- 가산점 항목 상태는 아래와 같습니다. 
    - [테블릿/폴더블/가로회전](https://github.com/nosorae/LezhinAssignment/issues/6) - 🟡 테블릿/가로만 테스트까지 해보고 폴더블 대응여부 모르는 상태
    - [다국어처리](https://github.com/nosorae/LezhinAssignment/issues/7) - 완료 (영어 추가) 🟢
    - [테마변경 대응](https://github.com/nosorae/LezhinAssignment/issues/8) - 완료 (Light/Dark) 🟢
    - Clean Architecture 적용 - 완료 🟢
        - (data -> domain <- presentation, 의존성 명확히 하기 위해 레이어별 멀티모듈로 구성) 
- 간단한 스크린샷 첨부


| ![검색화면](https://github.com/user-attachments/assets/4b2439b2-3df0-4aaf-933d-48820917e7a6) | ![북마크화면](https://github.com/user-attachments/assets/e7da08f2-1045-4999-a8c8-805c7732ccdd) | ![선택모드](https://github.com/user-attachments/assets/0d8ff79a-bfbc-4d17-be48-5f6724feb3e8) |
| :---: | :---: | :---: |
| 검색 화면 | 북마크 화면 | 북마크 화면 - 멀티 선택 모드 |

*시간부족으로 기능별 gif를 첨부하지 못했습니다

# 사용한 기술, 활용한 라이브러리에 대한 설명, 사용이유 등

- presentation layer
    - Material3 for Compose
        - Material Design 3 가이드라인을 따르는 UI 컴포넌트를 제공합니다.
        - (개인적으로는) 이렇게 디자인이 없을 때, 적은 노력으로도 구글의 UI/UX 가이드라인에 맞추어 일정수준 이상의 UI를 구성할 수 있어서 선호합니다.
            - (그러나 실무에서 디자인 요구사항을 맞출때는 Material3 컴포넌트 내부에 (minimum padding 같은) 제약이 있을 때가 많아, 대부분 직접
              구현합니다.)
        - 커스텀 테마 적용시 컴포넌트 내부적으로 들어간 디폴트 컬러나 텍스트 스타일의 일관성 유지에 주의해야합니다.
    - Coil for Compose
        - Kotlin Coroutine 기반으로 구현한 안드로이드를 위한 이미지 로딩 라이브러리입니다.
        - 이 프로젝트는 Coroutine과 OkHttp를 사용하고 있어서 Glide보다 적은 양의 메서드 수를 가져서 Glide보다 더 가볍다는 점에서 선택했습니다.
        - Glide가 Java기반이고 Coil이 Kotlin기반인 것도 한 몫합니다. Coil이 Kotlin 친화적인 것은 물론이고, Java 코드를 Kotlin 기반 프로젝트에서 사용할 때는 platform type 때문에 주의해야합니다.
        - (사소하게는) Glide for Compose 는 아직도 beta이고 Coil이 더 활발하게 개발되고 있어서 안정적이라 생각합니다.
        - 다만, Glide에는 있고 Coil에는 없는 기능이 있지는 않은지(ex. thumbnail image / full image 로드) 신중하게 확인하고 Coil을 선택해야할 것입니다.
    - AAC ViewModel (이하 ViewModel)
        - ViewModel은 Android의 수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리하는 컴포넌트입니다.
        - 화면 회전이나 테마 변경, 언어 변경같은 같은 구성 변경이 발생해도 데이터를 유지할 수 있어서 UI 상태관리에 용이합니다.
        - 또한 viewModelScope 같은 ViewModel의 생명주기를 따르는 CoroutineScope를 사용할 수 있어서 비동기 작업을 자동으로 취소할 수 있어서 사용했습니다.
        - ViewModel은 Activity/Fragment 보다 생명주기가 길기 때문에 ViewModel이 Activity나 Fragment의 참조를 가지면 메모리 누수를 일으킬 수 있어 주의해야합니다.
    - Jetpack Navigation
        - Jetpack Navigation은 Android 앱에서 화면 간 이동을 단순화하고 일관된 방식으로 관리할 수 있게 해주는 라이브러리입니다.
        - 화면이동 그래프를 직접 구현하면 생길 수많은 보일러플레이트 코드를 줄일 수 있어서 사용했습니다.
        - 해당 프로젝트에선 Compose를 통합한 'androidx.navigation:navigation-compose' 라이브러리를 사용했습니다.
        - Compose 에선 특히 owner 로 어느 ViewModelStoreOwner 구현체를 사용하는지 주의해야합니다. 그렇지 않으면 새로운 특정화면에서 뒤로갔다가 다시 진입해도 owner가 같아서 의도치 않게 이전에 생성된 ViewModel을 다시 사용하는 경우가 발생할 수 있습니다. (자세한 내용은 질의 섹션에서 다룹니다.)
    - Hilt Navigation Compose
        - Hilt Navigation Compose는 Jetpack Navigation과 Hilt를 통합하여 Compose에서 ViewModel을 사용할 수 있게 해주는 라이브러리입니다.
        - Navigation 백스택 엔트리에 맞춰 ViewModel의 범위를 관리해주기 위해 사용했습니다.
        - 이 역시 owner에 주의해야합니다. (자세한 내용은 질의 섹션에서 다룹니다.)
    - 기타 의존성
        - androidx.compose.ui
            - Jetpack Compose의 기본 UI 구성 요소를 제공하는 라이브러리입니다.
            - 레이아웃, 그리기, 입력 처리와 같은 기본 UI 기능을 포함하여 필수적으로 추가하였습니다.
        - androidx.compose.ui.graphics
            - Jetpack Compose에서 그래픽 관련 기능을 제공하는 라이브러리입니다.
            - 색상같은 그래픽 요소를 다뤄서 필수적으로 추가하였습니다.
        - androidx.activity.ktx
            - Activity와 관련된 작업을 Kotlin 확장 함수를 통해 더 간결하게 만듭니다.
            - ComponentActivity.enableEdgeToEdge()를 사용하여 따로 분기하지 않고 전체 화면 설정을 하기위해 사용했습니다.
        - androidx.ui.tooling.preview
            - Compose UI 개발을 위한 도구와 미리보기 기능을 제공합니다.
            - 기존 View 시스템에서 xml 의 미리보기 처럼 협업자에게 어떤 UI인지 직관적으로 보여줄 수 있어서 사용했습니다.
            - 미리보기 성능이 실제 디바이스와 다를 수 있으므로, 실제 디바이스에서 최종 테스트를 해봐야합니다.
- data layer
    - Retrofit2
        - 안드로이드 앱 개발에서 널리 사용되는 REST API 통신 라이브러리입니다.
        - 어노테이션을 사용하여 HTTP 메서드와 파라미터를 쉽게 정의할 수 있고 Coroutine과 함께 비동기 처리도 간편하게 할 수 있어 사용했습니다.
        - 다양한 Converter를 지원한다는 점도 있습니다.
        - 앱 난독화 시 Retrofit 관련 클래스가 제대로 동작하도록 ProGuard 규칙 설정에 주의해야합니다. 그렇지 않으면 JSON을 파싱하는 과정에서 예외가 발생합니다.
    - OkHttp3 logging interceptor
        - OkHttp 클라이언트의 HTTP 요청 및 응답을 로깅하는 인터셉터입니다.
        - 네트워크 통신의 디버깅과 모니터링을 용이하게 하기위해 사용했습니다.
    - Kotlin Serialization for Retrofit JSON converter
        - Retrofit2에서 JSON을 파싱하기 위한 Converter 중 하나입니다.
        - Kotlin 친화적인 라이브러리로, Kotlin의 Data Class와 함께 사용하기 용이합니다.
        - 컴파일 타임에 직렬화 코드를 생성하므로 런타임 오류를 줄일 수 있습니다.
        - 같은 맥락에서 런타임에 reflection을 사용하지 않으니 더 나은 런타임 성능을 기대하고 사용했습니다.
        - 그리고 디폴트 값을 지원하고 ignoreUnknownKeys 같은 옵션도 지원하여 편리합니다.
        - 서버에서 예상치 못한 데이터 형식을 반환할 때를 예상하고 옵션 설정에 주의해야합니다.
        - 또한 converter 와 kotlinx-serialization-json 의 버전호환에 주의해야합니다.
    - Room
        - SQLite 데이터베이스를 더 쉽게 사용할 수 있게 추상화 레이어를 제공하는 라이브러리입니다.
        - 기본적으로 컴파일 타임에 쿼리 검사를 제공하여 런타임 오류를 줄이고, Coroutine 으로 비동기 처리하고, Flow를 통해 데이터베이스 변경을 관찰하기 위해 사용했습니다.
        - 복잡한 쿼리의 경우 성능 최적화에 주의해야하고 원자적으로 수행해야할 때는 Transaction을 적절히 사용해야 데이터 불일치가 발생하지 않아서 이에 주의해야합니다.
- domain layer
    - javax.inject
        - Java의 DI를 위한 표준 어노테이션을 제공하는 패키지입니다.
        - domain 레이어에서 안드로이드 의존성 없이 DI를 사용하기 위해 사용했습니다.
    - kotlinx-coroutines-core
        - Kotlin 언어의 코루틴을 지원하기 위한 핵심 라이브러리입니다.
        - domain 레이어에서 비동기 작업 시 안드로이드 의존성 없이 Coroutine을 사용하기 위해 사용했습니다.
- common
    - Jetpack Paging3
        - 안드로이드 앱에서 대량의 데이터를 효율적으로 로드하고 표시하기 위한 라이브러리입니다.
        - 페이지네이션 구현에서 발생하는 (스크롤에 따른 다음 페이지 요청, 중간 페이지에서의 에러 처리, 구성변경에도 데이터를 유지하기위한 캐싱 등의) 보일러플레이트 코드를 줄이고, 다른 중요한 코드에 집중하기 위해 사용했습니다.
        - Clean Architecture 기반 프로젝트에서는 구현방식을 고민해봐야합니다. PagingSource를 data레이어에 두고 domain레이어의 (플랫폼 의존적이지 않은)순수함을 약간은 타협할지, presentation 레이어에 두고 domain레이어를 순수하게 지킬지 고민해봐야합니다.
        - 캐싱에 주의해야합니다. ViewModel을 유지하고 다른 화면을 다녀오거나 구성변경으로 인해 다시 구독할 때 캐싱해두지 않으면 불필요한 IO작업과 좋지 못한 UX가 발생할 수 있음에 주의해야합니다.
    - Dagger/Hilt
        - Dagger API를 Android에서 설치하고 설정하는 과정을 쉽게 해주는 오픈소스 래퍼 라이브러리입니다.
        - 안드로이드 구성요소에대해 컴포넌트 설정이 되어있어서 안드로이드 앱 개발할 때 보일러 플레이트 코드를 줄이기 위해 사용했습니다.
        - Koin과 비교했을 때 컴파일 타임에 의존성 그래프를 검증하여 런타임 오류를 줄일 수 있다는 장점도 있습니다.
    - Kotlin Coroutine/Flow
        - Kotlin Coroutine은 비동기 프로그래밍을 위한 Kotlin의 동시성 프레임워크입니다.
        - Flow는 Coroutine 기반의 비동기 데이터 스트림을 다루기 위한 API입니다.
        - Coroutine은 비동기 코드를 동기코드처럼 작성할 수 있어 직관적이고 가독성있는 코드를 작성할 수 있어서 사용했습니다.
        - Thread 를 직접 사용할 때와 비교했을 때 Context Switching 비용이 거의 없고 blocking 호출이 필요하지 않아 성능의 이점이 있어 사용했습니다.
        - 또한 다양한 라이브러리에서 Coroutine/Flow를 지원한다는 점도 있습니다.
        - Single Thread 가 아닐 때 critical section에 대한 동시성 이슈를 주의해야합니다.

# 질의

## Kotlin Coroutin/Flow 관련

### Cold Flow와 Hot Flow에 대해 자세히 설명해주세요.

- Cold Flow란?
    - Cold Flow는 구독자가 collect할 때마다 새로운(독립적인) 데이터를 생성하는 Flow입니다.
    - 각 구독자마다 독립적인 데이터 스트림을 가지고 구독자가 collect할 때만 활성화됩니다.
    - 즉 요청이 있을 때만 작업을 수행하고 아무것도 저장하지 않습니다.
    - 구독자가 있을때만 리소스를 사용하므로 더 효율적일 수 있습니다.
    - Flow의 기본동작입니다. 일반적으로 Hot Flow 보다 구현이 더 간단합니다.
    - 안드로이드 앱 개발할 때 대표적인 사용사례는 UseCase의 return 으로 Flow를 사용할 때입니다. UseCase를 호출할 때마다 새롭게 수행하고 데이터를 emit합니다. (물론 UseCase 에서 Hot Flow 로 반환할 수도 있지만 일반적으로는 Cold Flow 로 구현하게됩니다.)
- Hot Flow란?
    - Hot Flow는 구독자 유무와 관계없이 데이터를 지속적으로 생성할 수 있는 Flow입니다.
    - 즉 구독자가 없어도 계속해서 활성상태를 유지하여 계속해서 리소스를 사용할 수 있습니다.
    - 여러 구독자에게 동일한 데이터를 공유합니다.
    - 안드로이드 앱 개발할 때 대표적인 사용사례는 ViewModel에서 많이 사용하는 SharedFlow와 StateFlow입니다.
    - buffer, channelFlow와 같은 함수와 빌더는 Cole인 Flow도 Hot이 될 수 있게 지원합니다.

### SharedFlow와 StateFlow 에 대해 자세히 설명해주세요.

- SharedFlow 와 StateFlow 모두 Hot Flow로, 구독자 유무와 관계없이 데이터를 지속적으로 생성하고 여러 구독자에게 동일한 데이터를 공유합니다.
- 그러나 두 가지는 구체적인 구현과 사용사례가 다릅니다.
- SharedFlow
    - SharedFlow는 이벤트 스트림을 나타내는 Hot Flow입니다.
    - MutableSharedFlow를 통해 생성되며 초기값이 필요하지 않습니다.
        - MutableSharedFlow 팩토리 함수를 사용할 때 replayCache 사이즈는 물론 buffer 사이즈와 버퍼 초과 시 처리방식을 설정할 수 있습니다.
    - 또한 ShraedFlow는 replayCache 를 가지고 있어서 마지막으로 전송한 값들이 정해진 수만큼 저장되고 collect를 새롭게 시작하면 저장된 값들을 먼저 받고 이후에 emit 한 값을 추가로 받을 수도 있습니다.
    - Flow 의 확장함수인 shareIn 으로 Cold Flow를 ShraredFlow로 변환할 수도 있습니다.
        - 이 때 scope 파라미터에 CorutineScope를 인자로 전달해서 전달한 Scope가 취소될 때 함께 취소가능하게 하고
        - started 파라미터에 인자를 전달해서 활성 범위를 설정해야하고
        - replay 파라미터에 인자를 전달하여 replayCache의 사이즈를 설정해야합니다.
    - 상태보다는 이벤트를 나타내는 데 사용합니다. 예를 들어 ViewModel에서 화면에 토스트를 띄우는 이벤트라든지 naviagtion 이벤트를 전달할 때
      유용합니다.
- StateFlow
    - 한편 StateFlow는 최신 상태를 유지하는 Hot Flow라고 할 수 있습니다.
    - ShraedFlow의 개념을 확장시킨 것으로, 최신값을 value로 가지고 그 value 한 개를 원소로 하는 리스트를 replayCache로 갖습니다.
    - 즉 MutableSharedFlow 나 shareIn 에 replay 파라미터에 1을 인자로 준 것과 개념적으로 동일합니다.
    - StateFlow는 MutableStateFlow 팩토리함수를 통해 생성할 수 있으며 value가 항상 값을 가지고 있어야하므로 초기값을 전달해야합니다.
    - Flow 의 확장함수인 shareIn 으로 Cold Flow를 ShraredFlow로 변환할 수도 있습니다.
        - 이 때 scope 파라미터에 CorutineScope를 인자로 전달해서 전달한 Scope와 함께 취소가능하게 하고
        - started 파라미터에 인장를 전달해서 활성 범위를 결정하고
        - initialValue 파라미터에 인자를 전달하여 초기값을 설정해야합니다.
    - StateFlow는 상태를 나타내는 데 특화되어 있습니다. 예를 들어 ViewModel에서 화면에 표시할 데이터를 전달할 때 유용합니다.
    - 또한 마지막 값을 저장하고 있기 때문에 구성변경이 일어나서 새롭게 구독한다고 해도 즉시 최신 값을 전달하고 싶을 때도 사용합니다.
    - 그래서 UI 상태 홀더로 사용하기 적합하며, 기존 LiveData 를 대체하면서도 flow operator를 활용하여 더 많은 기능을 사용할 수 있습니다.
- 주의할 점은 StateFlow와 SharedFlow는 collect하면 (코루틴스코프가 취소되거나 하지 않으면) 무한히 실행될 수도 있다는 점입니다.
    - (StateFlow에 대응하는)LiveData 나 (SharedFlow에 대응하는 일명)SingleLiveData와 달리 Activity/Fragment같은 컴포넌트의
      생명주기를 모릅니다.
    - 따라서 구독하는 곳에서 생명주기가 있는 컴포넌트라면 repeatOnLifecycle 처럼 생명주기에 맞춰 구독하고 취소하는 매커니즘을 고려해야합니다.

## Android ViewModel 관련

### owner 에 대해 자세히 설명해주세요.

- ViewModel의 owner는 ViewModel Instance의 생명주기를 관리하는 객체를 말합니다.
- viewModel 함수를 사용할 때 전달하는 owner에 따라 새로운 ViewModel Instance를 생성하거나 기존의 ViewModel Instance를 가져올 수
  있습니다.
    - 예를 들어 Fragment 에서 부모 Activity의 ViewModel Instance를 사용하고자 viewModel 함수를 사용할 때 owner 로 부모 Activity를 전달한다면 부모 Activity에서 이미 생성된 ViewModel Instance를 가져올 수 있습니다.
    - 만약 이때 owner를 부모 Activity가 아닌 Fragment로 전달한다면 새로운 ViewModel Instance를 생성하게 됩니다.
- 여기서 owner는 ViewModelStoreOwner 인터페이스를 구현하는 클래스를 의미하고, 대표적으로 Activity/Fragment/NavBackStackEntry가 있습니다.
- ViewModelStoreOwner는 ViewModel Instance의 수명 주기를 관리하며, ViewModel Instance가 특정 UI 컴포넌트(Activity, Fragment 등)의 생명 주기와 함께 살아있도록 합니다.

### Compose Navigation + Hilt 조합에서 hiltViewModel()로 ViewModel Instance 가져올 때, owner를 어떻게 설정해야하는지 자세히 설명해주세요.
  
- hiltViewModel()를 사용하여 새로운 ViewModel Instance 를 생성하고 싶다면 owner를 따로 설정하지 않아도 됩니다.
    - LocalViewModelStoreOwner.current 가 디폴트 값으로 들어가고있는데,
    - 이때 LocalViewModelStoreOwner.current는 NavHost 내부적으로 화면 전환 시 CompositionalLocalProvider를 이용해서 LocalViewModelStoreOwner에 전환하는 NavBackStackEntry를 설정하기 때문입니다.
- 반면, hiltViewModel()를 사용하여 기존에 생성되어 있는 ViewModel Instance 를 가져오고 싶다면 원하는 스코프의 NavBackStackEntiry를 찾아서 owner로 전달해야합니다.
    - 이때 원하는 NavBackStackEntry는 NavHostController.getBackStackEntry에 destination id 를 전달하여 찾을 수 있습니다. (이미 백스택에 존재하는 destination의 id 여야합니다.)

## Android Paging3 관련

### PagingSource?

- PagingSource<Key, Value>는 Android Paging 라이브러리의 핵심 컴포넌트로, 데이터를 페이지 단위로 로드하는 방법을 정의합니다.
- Pager 컴포넌트 생성시 인자로 PagingSource객체를 인자로 전달합니다.
- 데이터를 페이지 단위로 로드하는 방식을 load로 정의하고 새로고침 시 어떤 키를 사용할지를 getRefreshKey로 정의합니다.

### PagingSource의 load 함수의 파라미터와 리턴 값에 대해 자세히 설명해 주세요.

- 인자로 LoadParams<Key> 정보를 받아 다음페이지를 로드하고 LoadResult<Key, Value>를 반환합니다.
- 이때 데이터 로드가 성공했을 때 LoadResult.Page 객체를 반환하고, 데이터 로드가 실패했을 때 LoadResult.Error 객체를 반환합니다.
- LoadResult.Page 객체는 로드한 데이터와 이전 페이지 키, 다음 페이지 키를 가지고 있습니다.
- LoadResult.Error 객체는 로드 실패한 이유를 Throwable로 전달할 수 있습니다.

### PagingSource의 getRefreshkey 함수의 파라미터와 리턴 값에 대해 자세히 설명해 주세요.

- getRefreshKey 함수의 파라미터타입은 PagingState<Key, Value> 입니다. 이는 로드된 Page들의 스냅샷과 마지막으로 접근한 anchorPosition을 포함하고 있습니다.
- public fun closestPageToPosition(anchorPosition: Int): Page<Key, Value>? 메서드로 LoadResult.Page<Key, Value> 를 얻어올 수 있습니다.
- closestPageToPosition 으로 얻어온 LoadResult.Page은 load 함수에서 로드 성공 시 반환했던 그 LoadResult.Page 타입입니다.
- 따라서 anchorPosition에 해당하는 LoadResult.Page의  nextKey, prevKey를 그대로 가지고 있고 이를 토대로 현재 key를 구해서 반환할 수 있습니다.
- 그러면 새로고침할 때 이 key가 load 메서드에 전달되어 데이터를 다시 로드할 수 있습니다.
