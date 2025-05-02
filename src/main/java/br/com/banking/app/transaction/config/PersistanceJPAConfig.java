package br.com.banking.app.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class PersistanceJPAConfig {
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("br.com.banking.app");
    factory.setDataSource(factory.getDataSource());
    
    return factory;
  }

  @Bean
  public PlatformTransactionManager platformTransactionManager(){
    JpaTransactionManager txManager = new JpaTransactionManager();

      txManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

      return txManager;
  }

}
