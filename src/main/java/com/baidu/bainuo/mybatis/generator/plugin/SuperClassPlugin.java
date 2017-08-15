/**
 * 
 */
package com.baidu.bainuo.mybatis.generator.plugin;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author zhujiawen
 * 
 */
public class SuperClassPlugin extends PluginAdapter {

    private FullyQualifiedJavaType superClass;
    private boolean addSuperClass;

    public SuperClassPlugin() {
        super();
    }

    public boolean validate(List<String> warnings) {
        // this plugin is always valid
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        addSuperClass = Boolean.valueOf(properties.getProperty("addSuperClass")); //$NON-NLS-1$
    }
    
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    protected void makeSerializable(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        
        if(addSuperClass) {
            superClass = new FullyQualifiedJavaType(properties.getProperty("superClass")); //$NON-NLS-1$
            topLevelClass.addImportedType(superClass);
            topLevelClass.setSuperClass(superClass);
        }
        
    }
}