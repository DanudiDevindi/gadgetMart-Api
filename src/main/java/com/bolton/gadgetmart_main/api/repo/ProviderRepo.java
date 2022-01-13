package com.bolton.gadgetmart_main.api.repo;

import java.util.List;

import com.bolton.gadgetmart_main.api.dto.ProviderDTO;

public interface ProviderRepo {
    boolean createProvider(ProviderDTO providerDTO) throws Exception;

    List<ProviderDTO> getAllProviders() throws Exception;

    boolean updateProvider(ProviderDTO providerDTO) throws Exception;

    boolean deleteProvider(ProviderDTO providerDTO) throws Exception;
}
