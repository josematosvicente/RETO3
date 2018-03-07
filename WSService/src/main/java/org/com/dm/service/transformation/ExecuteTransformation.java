package org.com.dm.service.transformation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.com.dm.exception.DmException;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class ExecuteTransformation {
	
	public List<LinkedHashMap> execKtr(LinkedHashMap<String, Object> param, String path)  throws DmException, Exception{
		
		try {
            KettleEnvironment.init();
            TransMeta metadata=new TransMeta(path);
            Trans trans=new Trans(metadata);
            
            /* Setting the Parameter Values 
             * @PARAM_ID : Parameter Name
             * @PARAM_NAME : Parameter Name (second parameter)
             */
            trans.setParameterValue("PARAM_ID", "1");
            trans.setParameterValue("PARAM_NAME", "Rishu"); 
            
          
            /* Executing or starting a .ktr file */
            trans.execute(null);
            trans.waitUntilFinished();
            
            if(trans.getErrors()>0){
                System.out.println("Erroruting Transformation");
            }
            
            return null;
            
     } catch (KettleException e) {
            e.printStackTrace();
            return null;
     }
	}
	

	public List<LinkedHashMap> execKjb(LinkedHashMap<String, Object> param, String fileName)  throws DmException, Exception{
		
//		String fileName="D:/ws_ktr/FORMATEADOR.kjb"; //Job file path that needs to be executed
        Repository repository=null; //Checking for repository
        
        try {
            KettleEnvironment.init();
            
            JobMeta jobmeta=new JobMeta(fileName,repository);
            Job job=new Job(repository, jobmeta);
            job.shareVariablesWith(jobmeta);
            /* Doing the basic initialization for executing a job */
            job.initializeVariablesFrom(null);
            job.getJobMeta().setInternalKettleVariables(job);
            job.copyParametersFrom(job.getJobMeta());

            /* Setting the Parameters to the job object
             * @Parameter Names: PARAM_ID and PARAM_NAME
             */
            
            /*
            for (int i = 0; i < param.size(); i++) {
            	job.setParameterValue(param.keySet()., param.get(i).toString());
			}
            */
            for(String key : param.keySet()){
            	job.setParameterValue(param.get(key).toString().split(":")[0], param.get(key).toString().split(":")[1]);
            }
            
            
//            job.setParameterValue("PARAM_NAME", "Rishu");
            

            /* Activating the Parameters and executing the Job */  
       
            job.activateParameters();
            job.execute(0, null);
            job.waitUntilFinished();
            
            if(job.getErrors()>0){
                System.out.println("Erroruting Job");
            }
            
            job.setFinished(true);
            return null;
                        
        } catch (KettleException e) {
           e.printStackTrace();
           return null;
        }
		
	}
	
	public List<LinkedHashMap> execute(LinkedHashMap<String, Object> param) throws DmException, Exception{
		String filePentaho = param.get("0").toString().replace("__", "/");
		String fileType = filePentaho.toString().substring(filePentaho.length()-3,filePentaho.length());
		System.out.println("fileType::"+fileType);
		if(fileType.equalsIgnoreCase("KTR")){
			execKtr(param, filePentaho);
		}else{
			execKjb(param, filePentaho);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		new ExecuteTransformation().execJob();
//	}
}
