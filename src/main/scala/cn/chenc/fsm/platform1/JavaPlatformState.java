package cn.chenc.fsm.platform1;

/**
 * Created by ChenC on 2016/9/6.
 */
public enum JavaPlatformState {
    //  定义state
    OPEN{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
        }
        @Override void valid(JavaPlatformMachine pm){
            this.exit(pm);
            if(pm.data.getValid_()){
                pm.state =STEP1;
            }else{
                NotFound();
                pm.state =OFF;
            }
            pm.state.entry(pm);
        }
    },

    OFF{
        @Override void over(JavaPlatformMachine pm){
            super.over(pm);
        }
    },
    STEP1{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
        }
        @Override void valid(JavaPlatformMachine pm){
            this.exit(pm);
            if(pm.data.getValid_()){
                pm.state =STEP1;
            }else{
                pm.state =NotFound;
            }
            pm.state.entry(pm);
        }
    },


    FANONLY{
        @Override void power(JavaPlatformMachine pm){
            this.exit(pm);
            stopFan();
            pm.state =OFF;
            pm.state.entry(pm);
        }
        @Override void cool(JavaPlatformMachine pm){
            this.exit(pm);
            pm.state =COOL;
            pm.state.entry(pm);
        }
    },
    COOL{
        @Override void exit(JavaPlatformMachine pm){
            super.exit(pm);
            stopCool();
        }
        @Override void entry(JavaPlatformMachine pm){
            startCool();
            super.entry(pm);
        }
        @Override void power(JavaPlatformMachine pm){
            this.exit(pm);
            stopFan();
            pm.state =OFF;
            pm.state.entry(pm);
        }
        @Override void cool(JavaPlatformMachine pm){
            this.exit(pm);
            pm.state =FANONLY;
            pm.state.entry(pm);
        }
    };



    //状态模式 提取的接口
    abstract void valid(JavaPlatformMachine pm);
    abstract void first(JavaPlatformMachine pm);
    abstract void businessLine(JavaPlatformMachine pm);
    abstract void district(JavaPlatformMachine pm);

    //状态机的各种动作action methode
    void entry(JavaPlatformMachine pm){System.out.println("→"+pm.state.name());}
    void exit(JavaPlatformMachine pm){System.out.println(pm.state.name()+"→ ");}
    void over(JavaPlatformMachine pm){System.out.println(pm.state.name()+"get over!");}


    void NotFound(){System.out.println("NotFound");}
    void ReturnDimension(){System.out.println("ReturnDimension");}
    void PreciseAdvertising(){System.out.println("PreciseAdvertising");}
    void Top9(){System.out.println("Top9");}



}
