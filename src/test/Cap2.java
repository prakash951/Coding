package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cap2 {
	
	public void doit()
	{
		ArrayList<NavuNode> portList = new ArrayList<>();
		Map<String, String> bareMetalComputeToPortId = new HashMap<>();
		Map<String, String> computeToNetworkId = new HashMap<>();
		Map<String, String> computeToTenantName = new HashMap<>();
		ExecutorService portService = Executors.newFixedThreadPool(100);
		for(NavuNode port:portList)
		{
			portService.submit(new PortInfoThread(port,bareMetalComputeToPortId,computeToNetworkId,computeToTenantName));
		}
		portService.shutdown();
	}

	static class PortInfoThread implements Callable<String> {

		private NavuNode port;
		private Map<String, String> bareMetalComputeToPortId;
		private Map<String, String> computeToNetworkId;
		private Map<String, String> computeToTenantName;

		public PortInfoThread(NavuNode navuNode, Map<String, String> bareMetalComputeToPortId,
				Map<String, String> computeToNetworkId, Map<String, String> computeToTenantName) {
			this.port = navuNode;
			this.bareMetalComputeToPortId = bareMetalComputeToPortId;
			this.computeToNetworkId = computeToNetworkId;
			this.computeToTenantName = computeToTenantName;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
//			if ("vts-ids:baremetal".equals(port.leaf("type").valueAsString())) {
//				String hostName = port.leaf("binding-host-id").valueAsString();
//				String portId = port.leaf("id").valueAsString();
//				bareMetalComputeToPortId.put(hostName, portId);
//			}
//			String hostName = port.leaf("binding-host-id").valueAsString();
//			String netWorkId = port.leaf("network-id").valueAsString();
//			// To get compute to netwrokid map
//			computeToNetworkId.put(hostName, netWorkId);
//			// To get tenant name correspanding to network id
//			NavuNode tenantName = port.leaf("network-id").getParent().getParent().getParent().getParent().getParent()
//					.getParent().getParent();
//			String tenantname = tenantName.leaf(NAME).valueAsString();
//			computeToTenantName.put(hostName, tenantname);
			return null;
		}

	}

}
