package com.clean.example.core.usecase.broadbandaccessdevice.getdetails;

import com.clean.example.core.entity.BroadbandAccessDevice;
import org.junit.Test;

import static com.clean.example.core.entity.DeviceType.ADSL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetBroadbandAccessDeviceDetailsUseCaseTest {

    GetDeviceDetails getDeviceDetails = mock(GetDeviceDetails.class);

    GetBroadbandAccessDeviceDetailsUseCase getBroadbandAccessDeviceDetailsUseCase = new GetBroadbandAccessDeviceDetailsUseCase(getDeviceDetails);

    @Test
    public void returnsDeviceDetails() throws Exception {
        BroadbandAccessDevice expectedDevice = givenADeviceIsFoundWithHostname("hostname1");

        BroadbandAccessDevice actualDevice = getBroadbandAccessDeviceDetailsUseCase.getDeviceDetails("hostname1");

        assertThat(actualDevice).isEqualTo(expectedDevice);
    }

    @Test
    public void errorWhenDeviceIsNotFound() throws Exception {
        givenADeviceIsNotFound();

        assertThatExceptionOfType(DeviceNotFoundException.class).isThrownBy(() -> getBroadbandAccessDeviceDetailsUseCase.getDeviceDetails("hostname1"));
    }

    private BroadbandAccessDevice givenADeviceIsFoundWithHostname(String hostname) {
        BroadbandAccessDevice expectedDevice = new BroadbandAccessDevice(hostname, "serialNumber", ADSL);
        when(getDeviceDetails.getDetails(hostname)).thenReturn(expectedDevice);
        return expectedDevice;
    }

    private void givenADeviceIsNotFound() {
        when(getDeviceDetails.getDetails(anyString())).thenReturn(null);
    }

}