import React from 'react'
import FromDatePicker from "../containers/lecturer/FromDatePicker";
import ToDatePicker from "../containers/lecturer/ToDatePicker";
import LecturerDropDown from "../containers/lecturer/LecturerDropDown";
import {Button, Grid, Icon} from 'semantic-ui-react'

const LecturerWorkloadFilter = () => (
    <div>
        <LecturerDropDown/>
        <Grid columns='two' divided>
            <Grid.Row>
                <Grid.Column>
                    <FromDatePicker/>
                    <ToDatePicker/>
                </Grid.Column>
                <Grid.Column>
                    <Button animated='vertical' primary>
                        <Button.Content visible>Экспортировать</Button.Content>
                        <Button.Content hidden>
                            <Icon name='download' />
                        </Button.Content>
                    </Button>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    </div>
);

export default LecturerWorkloadFilter;