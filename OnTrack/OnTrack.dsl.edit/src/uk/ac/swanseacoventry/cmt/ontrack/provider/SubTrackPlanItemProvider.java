/**
 */
package uk.ac.swanseacoventry.cmt.ontrack.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import uk.ac.swanseacoventry.cmt.ontrack.OntrackFactory;
import uk.ac.swanseacoventry.cmt.ontrack.OntrackPackage;
import uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan;
import uk.ac.swanseacoventry.cmt.ontrack.Track;

/**
 * This is the item provider adapter for a {@link uk.ac.swanseacoventry.cmt.ontrack.SubTrackPlan} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SubTrackPlanItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubTrackPlanItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addCriticalsPropertyDescriptor(object);
			addTracksPropertyDescriptor(object);
			addConnectorsPropertyDescriptor(object);
			addPointsPropertyDescriptor(object);
			addCrossingsPropertyDescriptor(object);
			addSignalsPropertyDescriptor(object);
			addTerminalsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Criticals feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCriticalsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_criticals_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_criticals_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__CRITICALS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Tracks feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTracksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_tracks_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_tracks_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__TRACKS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Connectors feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConnectorsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_connectors_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_connectors_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__CONNECTORS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Points feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPointsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_points_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_points_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__POINTS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Crossings feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCrossingsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_crossings_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_crossings_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__CROSSINGS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Signals feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSignalsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_signals_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_signals_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__SIGNALS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Terminals feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTerminalsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SubTrackPlan_terminals_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SubTrackPlan_terminals_feature", "_UI_SubTrackPlan_type"),
				 OntrackPackage.Literals.SUB_TRACK_PLAN__TERMINALS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(OntrackPackage.Literals.SUB_TRACK_PLAN__ENTRANCES);
			childrenFeatures.add(OntrackPackage.Literals.SUB_TRACK_PLAN__EXITS);
			childrenFeatures.add(OntrackPackage.Literals.SUB_TRACK_PLAN__TOPO_ROUTES);
			childrenFeatures.add(OntrackPackage.Literals.SUB_TRACK_PLAN__CONTROL_TABLE);
			childrenFeatures.add(OntrackPackage.Literals.SUB_TRACK_PLAN__RELEASE_TABLE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns SubTrackPlan.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SubTrackPlan"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		if (!(object instanceof SubTrackPlan))
			return getString("_UI_SubTrackPlan_type");
		else {
			ArrayList<String> cs = new ArrayList<String>();
			for(Track t : ((SubTrackPlan)object).getCriticals())
				cs.add(t.getName());
			return getString("_UI_SubTrackPlan_type") + " " + String.join(" ", cs);
		}
			
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(SubTrackPlan.class)) {
			case OntrackPackage.SUB_TRACK_PLAN__ENTRANCES:
			case OntrackPackage.SUB_TRACK_PLAN__EXITS:
			case OntrackPackage.SUB_TRACK_PLAN__TOPO_ROUTES:
			case OntrackPackage.SUB_TRACK_PLAN__CONTROL_TABLE:
			case OntrackPackage.SUB_TRACK_PLAN__RELEASE_TABLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.SUB_TRACK_PLAN__ENTRANCES,
				 OntrackFactory.eINSTANCE.createEntrance()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.SUB_TRACK_PLAN__EXITS,
				 OntrackFactory.eINSTANCE.createExit()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.SUB_TRACK_PLAN__TOPO_ROUTES,
				 OntrackFactory.eINSTANCE.createTopoRoute()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.SUB_TRACK_PLAN__CONTROL_TABLE,
				 OntrackFactory.eINSTANCE.createControlTableItem()));

		newChildDescriptors.add
			(createChildParameter
				(OntrackPackage.Literals.SUB_TRACK_PLAN__RELEASE_TABLE,
				 OntrackFactory.eINSTANCE.createReleaseTableItem()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return OntrackEditPlugin.INSTANCE;
	}

}
