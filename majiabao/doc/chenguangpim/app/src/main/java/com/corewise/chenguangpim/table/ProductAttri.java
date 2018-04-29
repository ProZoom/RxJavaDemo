package com.corewise.chenguangpim.table;

import org.litepal.crud.DataSupport;

/**
 * 作者：李阳
 * 时间：2018/4/28
 * 描述：产品属性封装
 */
public class ProductAttri extends DataSupport {


    private String deviceId;//标签id


    private String ExpansionJointForm;//膨胀节型式
    private String NominalDiameter;//公称直径
    private String TempSet;//设计温度
    private String PressureSet;//设计压力
    private String TestPressure;//试验压力
    private String AxialDisplacement;//轴向位移
    private String LateralDisplacement;//横向位移
    private String AngularDisplacement;//角向位移
    private String FatigueLife;//疲劳寿命
    private String AxialStiffness;//轴向刚度
    private String LateralStiffness;//横向刚度
    private String AngularStiffness;//角向刚度
    private String BellowsMaterial;//波纹管材质

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getExpansionJointForm() {
        return ExpansionJointForm;
    }

    public void setExpansionJointForm(String expansionJointForm) {
        ExpansionJointForm = expansionJointForm;
    }

    public String getNominalDiameter() {
        return NominalDiameter;
    }

    public void setNominalDiameter(String nominalDiameter) {
        NominalDiameter = nominalDiameter;
    }

    public String getTempSet() {
        return TempSet;
    }

    public void setTempSet(String tempSet) {
        TempSet = tempSet;
    }

    public String getPressureSet() {
        return PressureSet;
    }

    public void setPressureSet(String pressureSet) {
        PressureSet = pressureSet;
    }

    public String getTestPressure() {
        return TestPressure;
    }

    public void setTestPressure(String testPressure) {
        TestPressure = testPressure;
    }

    public String getAxialDisplacement() {
        return AxialDisplacement;
    }

    public void setAxialDisplacement(String axialDisplacement) {
        AxialDisplacement = axialDisplacement;
    }

    public String getLateralDisplacement() {
        return LateralDisplacement;
    }

    public void setLateralDisplacement(String lateralDisplacement) {
        LateralDisplacement = lateralDisplacement;
    }

    public String getAngularDisplacement() {
        return AngularDisplacement;
    }

    public void setAngularDisplacement(String angularDisplacement) {
        AngularDisplacement = angularDisplacement;
    }

    public String getFatigueLife() {
        return FatigueLife;
    }

    public void setFatigueLife(String fatigueLife) {
        FatigueLife = fatigueLife;
    }

    public String getAxialStiffness() {
        return AxialStiffness;
    }

    public void setAxialStiffness(String axialStiffness) {
        AxialStiffness = axialStiffness;
    }

    public String getLateralStiffness() {
        return LateralStiffness;
    }

    public void setLateralStiffness(String lateralStiffness) {
        LateralStiffness = lateralStiffness;
    }

    public String getAngularStiffness() {
        return AngularStiffness;
    }

    public void setAngularStiffness(String angularStiffness) {
        AngularStiffness = angularStiffness;
    }

    public String getBellowsMaterial() {
        return BellowsMaterial;
    }

    public void setBellowsMaterial(String bellowsMaterial) {
        BellowsMaterial = bellowsMaterial;
    }

    @Override
    public String toString() {
        return "ProductAttri{" +
                "ExpansionJointForm='" + ExpansionJointForm + '\'' +
                ", NominalDiameter='" + NominalDiameter + '\'' +
                ", TempSet='" + TempSet + '\'' +
                ", PressureSet='" + PressureSet + '\'' +
                ", TestPressure='" + TestPressure + '\'' +
                ", AxialDisplacement='" + AxialDisplacement + '\'' +
                ", LateralDisplacement='" + LateralDisplacement + '\'' +
                ", AngularDisplacement='" + AngularDisplacement + '\'' +
                ", FatigueLife='" + FatigueLife + '\'' +
                ", AxialStiffness='" + AxialStiffness + '\'' +
                ", LateralStiffness='" + LateralStiffness + '\'' +
                ", AngularStiffness='" + AngularStiffness + '\'' +
                ", BellowsMaterial='" + BellowsMaterial + '\'' +
                '}';
    }

    public String[] toStringArray(){


        return new String[]{ExpansionJointForm,NominalDiameter,TempSet,PressureSet,TestPressure,AxialDisplacement,LateralDisplacement,AngularDisplacement,FatigueLife,AxialStiffness,LateralStiffness,AngularStiffness,BellowsMaterial};
    }

}
